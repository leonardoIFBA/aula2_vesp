package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Aluno;
import model.Curso;
import repository.AlunoRepository;
import repository.CursoRepository;

public class AlunoService {
    private List<Aluno> listaAlunos;
    private List<Curso> listaCursos;
    private Scanner scanner;
    private AlunoRepository repoAluno;
    private CursoRepository repoCurso;
    
    public AlunoService() {
        this.listaAlunos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.repoAluno = new AlunoRepository();
        this.repoCurso = new CursoRepository();
        this.listaCursos = repoCurso.listarTodos();
    }

    public void listar(){
        //** busca no repositorio a lista de alunos cadastrados */
        listaAlunos = repoAluno.listarTodos();

        // testa se a lista esta vazia, se estiver sai do método
        if(listaAlunos.isEmpty()){
            System.out.println("Lista Vazia!");
            return;
        }

        System.out.println("\n***** Lista de alunos cadastrados *****");
        // Este for intera na lista de objetos e carrega o objeto
        // na variável c
        for(Aluno a: listaAlunos){
            System.out.println(a);
        }

        System.out.println("Total: " + listaAlunos.size() + " cursos");
    }

    public void adicionar(){
        // *** Lê as informações digitadas pelo usuário
        System.out.println("\n--- Novo Aluno ---");
        System.out.println("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o email do aluno: ");
        String email = scanner.nextLine();
        System.out.println("Digite o idade do aluno: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o CPF do aluno: ");
        String cpf = scanner.nextLine();
        // Listar os cursos da base de dados. Variável listaCurso
        System.out.println("\n=== Lista de cursos ===");
        for(Curso c : listaCursos)
            System.out.println("     " + c);

        System.out.println("Escolha ID do curso: ");
        int idCurso = scanner.nextInt();

        Curso cursoSelecionado = null;
        for(Curso c : listaCursos){
            if(c.getId() == idCurso){
                cursoSelecionado = c;
                break;
            }
        }      

        //*** Cria um objeto novoAluno e carrega as informações */
        Aluno novoAluno = new Aluno(0, nome, email, idade, cpf, cursoSelecionado);
        //*** Manda o objeto para o repositório para ser salvo
        repoAluno.salvar(novoAluno);

        System.out.println("Novo aluno adicionado com sucesso!");
    }

    public void excluir() {
        listar();
        if(listaAlunos.isEmpty())
            return;

        System.out.println("Digite o ID do aluno para excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removido = listaAlunos.removeIf(a -> a.getId() == id);

        if(removido)
            System.out.println("Aluno deletado com sucesso!!!");
        else
            System.err.println("Aluno não encontrado.");
    }

    public void atualizar(){
        //** Carrega a lista do "BD" */
        listar();
        /** sai do método se a lista for vazia */
        if (listaAlunos.isEmpty())
            return;
        
        /*** pega o id do curso que deseja atualizar */
        System.out.println("Digite o ID do aluno para atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Aluno alunoSelecionado = null; //cria um aluno temporário
        /*** percorre a lista de alunos até encontrar o ID que deseja atualizar */
        for(Aluno a : listaAlunos){
            if(a.getId() == id){
                alunoSelecionado = a;
                break;
            }
        }

        /*** retorna mensagem ao usuário caso não encontre o ID */
        if (alunoSelecionado == null){
            System.out.println("Curso não encotrado!");
            return;
        }

        /***  atualiza os campos do aluno */
        System.out.println("\n --- Atualizando aluno: " + 
                            alunoSelecionado.getNome());
        
        System.out.println("Novo nome (Enter para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()){
            alunoSelecionado.setNome(nome);
        }

        System.out.println("Nova idade (0 para manter): ");
        int idade = scanner.nextInt();
        if (idade > 0){
            alunoSelecionado.setIdade(idade);
        }

        System.out.println("Novo CPF (Enter para manter): ");
        String cpf = scanner.nextLine();
        if (!cpf.isEmpty()){
            alunoSelecionado.setCpf(cpf);
        }

        // Listar os cursos da base de dados. Variável listaCurso
        System.out.println("\n=== Lista de cursos ===");
        for(Curso c : listaCursos)
            System.out.println("     " + c);

        System.out.println("Escolha ID do curso: ");
        int idCurso = scanner.nextInt();

        Curso cursoSelecionado = null;
        for(Curso c : listaCursos){
            if(c.getId() == idCurso){
                cursoSelecionado = c;
                break;
            }
        }   
        
        if (cursoSelecionado.getId() > 0){
            alunoSelecionado.setCurso(cursoSelecionado);
        }

        /***  salva as alterações aplicadas */
        //repoCurso.salvar(cursoSelecionado);

        scanner.nextLine();

        System.out.println("Aluno atualizado com sucesso!");
    }
    
    
}
