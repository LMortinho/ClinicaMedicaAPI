package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record DadosListagemPaciente(Boolean ativo, Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getAtivo(), paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}
