package Packages;

public class Doador {
    private String nome;
    private String cpf;
    private String tipoSanguineo;
    private String dataDoacao;

    // Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }
    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getDataDoacao() {
        return dataDoacao;
    }
    public void setDataDoacao(String dataDoacao) {
        this.dataDoacao = dataDoacao;
    }
}
