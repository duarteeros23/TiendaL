package com.erosduarte.Tienda.config;
import com.erosduarte.Tienda.entity.Usuarios;
import com.erosduarte.Tienda.repository.UsuariosRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsuariosRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UsuariosRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")
                        .requestMatchers("/productos/**", "/ventas/**", "/clientes/**", "/detalleVentas/**")
                        .hasAnyRole("ADMIN", "VENDEDOR", "USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .usernameParameter("correoUsuario")
                        .passwordParameter("contrasenaUsuario")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/403")
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return email -> {
            Usuarios usuario = usuarioRepository.findByCorreoUsuario(email)
                    .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado: " + email));

            return User.builder()
                    .username(usuario.getCorreoUsuario())
                    .password(usuario.getContrasenaUsuario())
                    .roles(usuario.getRol())
                    .build();
        };
    }


}
