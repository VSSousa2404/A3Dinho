package Packages;

public class Denuncia {
    private String titulo;
    private String nomeProfissionalOuInstituicao;
    private String dataOcorrido;
    private String localOcorrido;
    private String descricaoDetalhada;
    private String protocolo;

    // Getters e Setters
    public String getTitulo() { 
        return titulo; 
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo; 
    }

    public String getNomeProfissionalOuInstituicao() { 
        return nomeProfissionalOuInstituicao; 
    }
    
    public void setNomeProfissionalOuInstituicao(String nome) { 
        this.nomeProfissionalOuInstituicao = nome; 
    }

    public String getDataOcorrido() { 
        return dataOcorrido; 
    }
    
    public void setDataOcorrido(String data) { 
        this.dataOcorrido = data; 
    }

    public String getLocalOcorrido() { 
        return localOcorrido; 
    }
    
    public void setLocalOcorrido(String local) { 
        this.localOcorrido = local; 
    }

    public String getDescricaoDetalhada() { 
        return descricaoDetalhada; 
    }
    
    public void setDescricaoDetalhada(String descricao) { 
        this.descricaoDetalhada = descricao; 
    }

    public String getProtocolo() { 
        return protocolo; 
    }
    
    public void setProtocolo(String protocolo) { 
        this.protocolo = protocolo; 
    }
}
