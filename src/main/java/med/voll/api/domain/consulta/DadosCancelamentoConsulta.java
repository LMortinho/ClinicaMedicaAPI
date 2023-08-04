package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull(message = "Id da consulta é obrigatório!")
        Long idConsulta,
        @NotNull(message = "Motivo da consulta é obrigatório!")
        motivoCancelamento motivo) {
}
