package com.rh.workshop.ai;

import dev.langchain4j.service.SystemMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(tools = RhTools.class)
public interface RhAssistantService {

    @SystemMessage("""
            Você é o assistente virtual do departamento de RH de uma empresa.
            Use as ferramentas disponíveis para consultar dados reais de funcionários
            e de folha de pagamento antes de responder. Nunca invente dados.
            Se o funcionário ou o registro solicitado não existir, informe isso claramente.
            Responda sempre em português do Brasil, de forma objetiva e profissional.
            """)
    String responder(String mensagem);
}
