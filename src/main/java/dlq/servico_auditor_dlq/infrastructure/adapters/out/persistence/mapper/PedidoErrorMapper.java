package dlq.servico_auditor_dlq.infrastructure.adapters.out.persistence.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dlq.servico_auditor_dlq.core.domain.bo.PedidoErrorBO;
import dlq.servico_auditor_dlq.infrastructure.adapters.out.persistence.entity.PedidoErrorEntity;


public class PedidoErrorMapper {

  private static ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();;

  public PedidoErrorMapper(ObjectMapper objectMapper) {
    PedidoErrorMapper.objectMapper = objectMapper;
  }

  public static PedidoErrorEntity toEntity(PedidoErrorBO BO) throws JsonProcessingException {

    PedidoErrorEntity entity = new PedidoErrorEntity();

    entity.setQueueName(BO.getOrigin());

    entity.setTimestamp(BO.getOccurredAt().toString());

    entity.setStatus("PENDING_ANALYSIS");

    entity.setSeverity(BO.definirSeveridade());

    entity.setPayload(objectMapper.writeValueAsString(BO));

    return entity;
  }

  public static PedidoErrorBO toBO(PedidoErrorEntity entity) throws JsonProcessingException {

    
    return objectMapper.readValue(entity.getPayload(), PedidoErrorBO.class);
    
  }
}
