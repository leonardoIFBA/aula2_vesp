package repository;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Curso;

public class AlunoRepository {
    private List<Aluno> alunos = new ArrayList<>();
    private static int contadorId = 1;

    public AlunoRepository(){
        Curso c = new Curso(0, "Teste", 100);
        //** Adicionar cursos pra testar o listarTodos */
        alunos.add(new Aluno(contadorId++, "João", "joao@mail", 20, "111.222.333-01",c));
        alunos.add(new Aluno(contadorId++, "Maria", "maria@mail", 22, "111.222.333-02",c));
        alunos.add(new Aluno(contadorId++, "Bruxa","bruxa@mail", 1100, "111.222.333-03",c));
    }

    public void salvar(Aluno aluno){
        aluno.setId(contadorId++);
        alunos.add(aluno);
    }

    public void excluir(Aluno aluno){
        alunos.remove(aluno);
    }

    public List<Aluno> listarTodos(){
        return alunos;
    }
    
}
