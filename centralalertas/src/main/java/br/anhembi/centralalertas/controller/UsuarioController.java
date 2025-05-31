package br.anhembi.centralalertas.controller;

import br.anhembi.centralalertas.model.Alerta;
import br.anhembi.centralalertas.model.Usuario;
import br.anhembi.centralalertas.repository.AlertaRepository;
import br.anhembi.centralalertas.repository.UsuarioRepository;
import br.anhembi.centralalertas.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @GetMapping("/usuarios")
public List<Usuario> listarUsuarios() {
    return usuarioRepository.findAll();
}

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AlertaRepository alertaRepository;

    @PostMapping("/register")
    public String registrar(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
        return "Usuário registrado com sucesso!";
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        Optional<Usuario> encontrado = usuarioRepository.findByUsernameAndSenha(usuario.getUsername(), usuario.getSenha());
        return encontrado.isPresent() ? "Login bem-sucedido!" : "Credenciais inválidas.";
    }

 @Autowired
private EmailService emailService;

@PostMapping("/verificar-suspeita/{userId}")
public String verificarSuspeita(@PathVariable UUID userId, @RequestBody String mensagem) {
    Optional<Usuario> usuarioOpt = usuarioRepository.findById(userId);
    if (usuarioOpt.isPresent()) {
        Usuario usuario = usuarioOpt.get();
        if (mensagem.toLowerCase().contains("pix") || mensagem.toLowerCase().contains("urgente")) {
            Alerta alerta = new Alerta();
            alerta.setMensagem(mensagem);
            alerta.setUsuario(usuario);
            alertaRepository.save(alerta);

            // Enviar e-mail
            emailService.enviarEmail(
                usuario.getEmail(),
                "Alerta de possível golpe",
                "Detectamos uma mensagem suspeita para o usuário: " + usuario.getUsername() + "\n\nMensagem: " + mensagem
            );

            return "Alerta registrado e notificação enviada.";
        }
        return "Nenhum comportamento suspeito detectado.";
    }
    return "Usuário não encontrado.";
}
}