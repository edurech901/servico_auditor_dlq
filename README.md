#Serviço auditor DLQ

O serviço auditor DLQ é responsável consumir mensagens que deram erro na fila principal e guardar as informações para análise futura. As informações salvas são o payload, a data e hora do erro, o status, a fila e uma regra de negócio que define a seriedade do erro.

#Arquitetura hexagonal

A arquitetura aplicada foi a arquitetura hexagonal. Por se tratar de um serviço que recebe informações externas, uma arquitetura com baixo acoplamento é uma ótima escolha, separando as regras de negócio das conexões com a AWS e o banco de dados.

#Fluxo de funcionamento

Json é enviado à nossa fila principal, se o serviço principal não conseguir receber essa mensagem em três tentativas (quantidade configurada na AWS), é enviada a fila do DLQ. A partir disso, O Listener consome da fila DLQ, que recebe uma DTO, passa pelo mapper de DTO para BO, executa a regra de negócio para definir a severidade, passa pelo mapper de BO para Entity e é persistido no banco

