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
import br.anhembi.alertaemail.dto.response;
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
    private NotificacaoRequestProducer producer;

    @Autowired
    private repo repo;

    @Autowired    
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;


    


 public void processarVazio (response dto){
    boolean suspeita = dto.setSuspeita(dto.isSuspeita());
    dto.setUserId(dto.getUserId());
    String email = dto.setEmail(dto.getEmail());
    dto.setNotifId(dto.getNotifId());
    System.out.println("Envio por barramento: " + dto.isSuspeita() + " UserId: " + dto.getUserId() + " Destinatario: " + dto.getEmail()+ " NotifId: " + dto.getNotifId());
    
            String subject = "Alerta de transação suspeita";
            String mensagem = "Foi detectada uma atividade suspeita em sua conta. " +
                            "Por favor, verifique suas transações recentes. " +
                            "Se você não reconhece essa atividade, entre em contato conosco imediatamente.";

if(suspeita==true){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetente);
            message.setTo(email);
            message.setSubject(subject);
            message.setText(mensagem);
            mailSender.send(message);

        

            model model = new model();
            model.setNotifId(dto.getNotifId());
            model.setUserId(dto.getUserId());
            model.setEmail(email);
            model.setData(LocalDate.now());
            model.setAssunto(subject);
            model.setMensagem("Email enviado para " + email + " com assunto: " + subject);
            repo.save(model);

            eventoDTO evento = new eventoDTO();
            
            evento.setUserId(dto.getUserId());
            evento.setNotifId(dto.getNotifId());
            evento.setAlertaId(repo.findTopByUserIdOrderByAlertaIdDesc(dto.getUserId()).get().getAlertaId());
            evento.setMensagem("Email enviado para " + email + " com assunto: " + subject);
            evento.setData(LocalDate.now());
            evento.setEmail(dto.getEmail());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<eventoDTO> request = new HttpEntity<eventoDTO>(evento, headers);

            
            restTemplate.postForEntity("http://localhost:8082/api/eventos", request, String.class);
            System.out.println("Evento enviado ao barramento");
            }catch (Exception e) {
           System.out.println("Erro ao enviar o email: " + e.getMessage());
    }
}


}





 public void processarDto(emailDto dto) {
        if (repo.existsByNotifId(dto.getNotifId())) {
            System.out.println("Alerta já processado: " + dto.getNotifId());
        }else if (dto.getNotifId() == null) {
            producer.solicitarUltimaNotificacaoSuspeita(dto.getUserId(), dto.getEmail());
        }else{
            this.dto.setSuspeita(dto.isSuspeita());
            this.dto.setUserId(dto.getUserId());
            this.dto.setEmail(dto.getEmail());
            this.dto.setNotifId(dto.getNotifId());
            System.out.println("Recebido: " + dto.isSuspeita() + " UserId: " + dto.getUserId() + " Destinatario: " + dto.getEmail()+ " NotifId: " + dto.getNotifId());
            sendEmail(dto.getEmail());

            }


        }
    



        

    public String sendEmail(String email) {


        if(this.dto.isSuspeita()){
            String subject = "Alerta de transação suspeita";
            String mensagem = "Foi detectada uma atividade suspeita em sua conta. " +
                            "Por favor, verifique suas transações recentes. " +
                            "Se você não reconhece essa atividade, entre em contato conosco imediatamente.";
            email = dto.getEmail();

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(remetente);
            message.setTo(email);
            message.setSubject(subject);
            message.setText(mensagem);
            mailSender.send(message);

        

            model model = new model();
            model.setAlertaId(dto.getAlertaId());
            model.setNotifId(dto.getNotifId());
            model.setUserId(dto.getUserId());
            model.setEmail(email);
            model.setData(LocalDate.now());
            model.setAssunto(subject);
            model.setMensagem("Email enviado para " + email + " com assunto: " + subject);
            repo.save(model);

            eventoDTO evento = new eventoDTO();
            
            evento.setUserId(dto.getUserId());
            evento.setNotifId(dto.getNotifId());
            evento.setAlertaId(repo.findTopByUserIdOrderByAlertaIdDesc(dto.getUserId()).get().getAlertaId());
            evento.setMensagem("Email enviado para " + email + " com assunto: " + subject);
            evento.setData(LocalDate.now());
            evento.setEmail(dto.getEmail());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<eventoDTO> request = new HttpEntity<eventoDTO>(evento, headers);

            
            restTemplate.postForEntity("http://localhost:8082/api/eventos", request, String.class);
            System.out.println("Evento enviado ao barramento");
            }catch (Exception e) {
           System.out.println("Erro ao enviar o email: " + e.getMessage());
           return "Erro ao enviar o email";
        }
    

    }else{
        System.out.println("Email não enviado, pois não é suspeita: " + dto.getEmail());
    }
    

    return "Email enviado com sucesso para " + dto.getEmail();
    }


}






