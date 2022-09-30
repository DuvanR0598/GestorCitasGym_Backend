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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udea.energym.security.configuration.Authority;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cedula")
	private Long cedula;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "genero")
	private char genero;
	
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "peso")
	private float peso;
	
	@Column(name = "altura")
	private float altura;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioRolEntity> roles = new HashSet<>();
    
    @OneToMany(mappedBy = "usuarioEnt", cascade = CascadeType.ALL)
    @JsonIgnore
	private Set<CitaEntity> citas = new HashSet<>();

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
}