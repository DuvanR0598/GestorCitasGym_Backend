package com.udea.energym.persistence.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udea.energym.security.configuration.Authority;
import com.udea.energym.util.validaciones.ValidEmail;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cedula")
	private Long cedula;
	
	@Size(min = 1, max = 15)
	@Column(name = "nombre")
	private String nombre;
	
	@Size(min = 1, max = 15)
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "genero")
	private char genero;
	
	@Past(message = "La fecha de nacimiento debe ser en el pasado")
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@Column(name = "celular")
	private String celular;
	
	@ValidEmail(allowedDomains = {"com", "net", "edu"})
	@NotBlank(message = "El correo electrónico no puede estar vacío")
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "peso")
	private float peso;
	
	@Column(name = "altura")
	private float altura;
	
	@Size(min = 4, max = 10)
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioRolEntity> roles = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioMembresiaEntity> membresias = new HashSet<>();
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuarioEnt")
    @JsonIgnore
    private Set<UsuarioClasesEntity> clases = new HashSet<>();

    //------------------------------------------------------------------------------------------
    
    public Set<UsuarioClasesEntity> getClases() {
		return clases;
	}

	public void setClases(Set<UsuarioClasesEntity> clases) {
		this.clases = clases;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}

	public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Obtenemos los roles, recorremos la tabla de los roles y retornamos el nombre
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> autoridades = new HashSet<>();
        this.roles.forEach(usuarioRol -> {
            autoridades.add(new Authority(usuarioRol.getRol().getNombre()));
        });
        return autoridades;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return true;
    }
    
	public Set<UsuarioRolEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<UsuarioRolEntity> roles) {
		this.roles = roles;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<UsuarioMembresiaEntity> getMembresias() {
		return membresias;
	}

	public void setMembresias(Set<UsuarioMembresiaEntity> membresias) {
		this.membresias = membresias;
	}
	
	
}