package model;

public class Aluno extends Pessoa{
    private int idade;
    private String cpf;
    private Curso curso;

    public Aluno(int id, String nome, String email, int idade, String cpf, Curso curso){
        super(id, nome, email);
        this.idade = idade;
        this.cpf = cpf;
        this.curso = curso;
    }
       
    @Override
    public String toString() {
        return "Aluno [" + super.toString() + ", idade=" + idade + 
                ", cpf=" + cpf + ", curso=" + curso.getNome() + "]";
    }

    @Override
    public double calculaBonus() {
        // TODO Auto-generated method stub
        return 0;
    }
    //getters e Setters
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }


    
}
