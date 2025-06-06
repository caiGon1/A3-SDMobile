Trabalho A3 - Sistemas Distribuidos e Mobile com parceira com Bradesco com o tema: GOLPES.
============================================================================================
Integrantes: 
Caio Gonçalves
Daniel Cardoso
Gian Tozzatti
Guilherme Mendes

============================================================================================

PUBLICO ALVO: Idosos e população mais vulnerável

============================================================================================

GUIA DO PROGRAMA:

Após o login feito com um objeto JSON com os dados "user" e "senha", após a verificação correta
o programa começa a rodar com o microserviço "notificação" que verifica as ultimas transações
e compara a média delas com a última transação, caso ela seja maior que 50% ele dispara um alerta
para o microserviço "alertaemail" que envia uma notificação para o email cadastrado. Todos os serviços
disparam também um alerta HTTP para o microserviço de "eventos/barramento", que armazena os eventos para
futuro uso.

Caso há algum problema no microserviço de "notificação/verificação", os microserviços de login e email mudam
utilizando dados vindo do banco de dados do barramento de eventos por intermédio do Docker e Kafka para enviar
emails caso há alguma pendência de notificação armazenada.

==============================================================================================

