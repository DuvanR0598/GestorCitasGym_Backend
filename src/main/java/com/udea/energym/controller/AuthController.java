package com.udea.energym.controller;

import com.udea.energym.dto.Login;
import com.udea.energym.dto.Usuario;
import com.udea.energym.persistence.entity.RolEntity;
import com.udea.energym.persistence.entity.UsuarioEntity;
import com.udea.energym.persistence.entity.UsuarioRolEntity;
import com.udea.energym.persistence.repository.IUsuarioRepository;
import com.udea.energym.security.CustomUserDetailsService;
import com.udea.energym.security.JWTAuthResonseDTO;
import com.udea.energym.security.JwtTokenProvider;
import com.udea.energym.service.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*") //permite el intercambio de recursos(solicitudes) entre Back y Front
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
	private IUsuarioRepository usuarioRepositorio;
    
    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
	private PasswordEncoder passwordEncoder;

    @PostMapping("/iniciar-sesion")
    public ResponseEntity<?> generarToken(@RequestBody Login login) throws Exception {
        try{
            autenticar(login.getUsername(),login.getPassword());
        }catch (Exception exception){
            exception.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails =  userDetailsService.loadUserByUsername(login.getUsername());
        String token = jwtTokenProvider.generateToken(userDetails);
        return ResponseEntity.ok(new JWTAuthResonseDTO(token));
    }

    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario){
    	if(Boolean.TRUE.equals(usuarioRepositorio.existsByUsername(usuario.getUsername()))) {
			return new ResponseEntity<>("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}else {
			Set<UsuarioRolEntity> usuarioRoles = new HashSet<>();

	        RolEntity rol = new RolEntity();
	        rol.setDniRol(1L);
	        rol.setNombre("ROLE_ADMIN");

	        UsuarioEntity usuarioEnt = guardarUsuario(usuario);
	        
	        UsuarioRolEntity usuarioRolEnt = new UsuarioRolEntity();
	        usuarioRolEnt.setUsuario(usuarioEnt);
	        usuarioRolEnt.setRol(rol);

	        usuarioRoles.add(usuarioRolEnt);
	        usuarioService.guardarUsuario(usuarioEnt, usuarioRoles);
	        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
		}
    }
    
    @GetMapping("/usuario-actual")
    public UsuarioEntity obtenerUsuarioActual(Principal principal){
        return (UsuarioEntity) this.userDetailsService.loadUserByUsername(principal.getName());
    }
    
    private UsuarioEntity guardarUsuario(Usuario usuario) {
		UsuarioEntity usuarioEnt = new UsuarioEntity();
		
		usuarioEnt.setCedula(usuario.getCedula());
		usuarioEnt.setNombre(usuario.getNombre());
		usuarioEnt.setApellido(usuario.getApellido());
		usuarioEnt.setGenero(usuario.getGenero());
		usuarioEnt.setFechaNacimiento(usuario.getFechaNacimiento());
		usuarioEnt.setCelular(usuario.getCelular());
		usuarioEnt.setEmail(usuario.getEmail());
		usuarioEnt.setPeso(usuario.getPeso());
		usuarioEnt.setAltura(usuario.getAltura());
		usuarioEnt.setUsername(usuario.getUsername());
		usuarioEnt.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		return usuarioEnt;
	}
}
