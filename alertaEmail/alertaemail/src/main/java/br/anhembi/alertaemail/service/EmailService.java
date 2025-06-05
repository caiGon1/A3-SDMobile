package br.anhembi.alertaemail.service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import br.anhembi.alertaemail.dto.emailDto;
import br.anhembi.alertaemail.dto.eventoDTO;
import br.anhembi.alertaemail.model.model;
import br.anhembi.alertaemail.repo.repo;

@Service
public class EmailService {

    emailDto dto = new emailDto();
    
    private final RestTemplate restTemplate;

    public EmailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Autowired
    private repo repo;

    @Autowired    
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public boolean processarDto(emailDto dto) {

        if(repo.existsByNotifId(dto.getNotifId())){
            System.out.println("Alerta já processado: " + dto.getNotifId());
            return false;
        }else{
        this.dto.setSuspeita(dto.isSuspeita());
        this.dto.setUserId(dto.getUserId());
        this.dto.setDestinatario(dto.getDestinatario());
        this.dto.setNotifId(dto.getNotifId());
        System.out.println("Recebido: " + dto.isSuspeita() + " UserId: " + dto.getUserId() + " Destinatario: " + dto.getDestinatario()+ " NotifId: " + dto.getNotifId());
        sendEmail(dto.getDestinatario());
        return true;
    }
        
}





    public String sendEmail(String destinatario) {
        

        if(dto.isSuspeita()){
            String subject = "Alerta de transação suspeita";
        String mensagem = "Foi detectada uma atividade suspeita em sua conta. " +
                            "Por favor, verifique suas transações recentes. " +
                            "Se você não reconhece essa atividade, entre em contato conosco imediatamente.";
            destinatario = dto.getDestinatario();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetente);
            message.setTo(destinatario);
            message.setSubject(subject);
            message.setText(mensagem);
            mailSender.send(message);

            eventoDTO evento = new eventoDTO();
            
            evento.setUserId(dto.getUserId());
            evento.setNotifId(dto.getNotifId());
            evento.setMensagem("Email enviado para " + destinatario + " com assunto: " + subject);
            evento.setAlertaId(dto.getAlertaId());
            evento.setData(LocalDate.now());
            evento.setEmail(destinatario);

            model model = new model();
            model.setAlertaId(dto.getAlertaId());
            model.setNotifId(dto.getNotifId());
            model.setUserId(dto.getUserId());
            model.setEmail(destinatario);
            model.setData(LocalDate.now());
            model.setDestinatario(destinatario);
            model.setAssunto(subject);
            model.setMensagem("Email enviado para " + destinatario + " com assunto: " + subject);
            repo.save(model);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<eventoDTO> request = new HttpEntity<eventoDTO>(evento, headers);

            try {
            restTemplate.postForEntity("http://localhost:8082/api/eventos", request, String.class);
            System.out.println("Evento enviado ao barramento");
            } catch (Exception e) {
            System.err.println("Erro ao enviar evento: " + e.getMessage());
    }
            

        } catch (Exception e) {
           System.out.println("Erro ao enviar o email: " + e.getMessage());
           return "Erro ao enviar o email";
        }

    }else{
        System.out.println("Email não enviado, pois não é suspeita: " + dto.getDestinatario());
    }

    return "Email enviado com sucesso para " + dto.getDestinatario();

}
}


