package repository;

import java.util.ArrayList;
import java.util.List;

import model.Curso;

public class CursoRepository {
    private List<Curso> cursos = new ArrayList<>();
    private static int contadorId = 1;

    public CursoRepository(){
        //** Adicionar cursos pra testar o listarTodos */
        cursos.add(new Curso(contadorId++, "Informatica", 1200));
        cursos.add(new Curso(contadorId++, "Edificacoes", 1200));
        cursos.add(new Curso(contadorId++, "Mineracao", 1100));
    }

    public void salvar(Curso curso){
        curso.setId(contadorId++);
        cursos.add(curso);
    }

    public void excluir(Curso curso){
        cursos.remove(curso);
    }

    public List<Curso> listarTodos(){
        return cursos;
    }
}
