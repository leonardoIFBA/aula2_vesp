package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Curso;
import repository.CursoRepository;

public class CursoService {
    private List<Curso> listaCursos;
    private Scanner scanner;
    private CursoRepository repoCurso;

    public CursoService(){
        this.listaCursos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.repoCurso = new CursoRepository();
    }

    public void excluir() {
        listar();
        if(listaCursos.isEmpty())
            return;

        System.out.println("Digite o ID do curso para excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removido = listaCursos.removeIf(c -> c.getId() == id);

        if(removido)
            System.out.println("Curso deletado com sucesso!!!");
        else
            System.err.println("Curso não encontrado.");
    }
    
    public void adicionar(){
        // *** Lê as informações digitadas pelo usuário
        System.out.println("\n--- Novo Curso ---");
        System.out.println("Digite o nome do curso: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a duração do curso: ");
        int duracao = scanner.nextInt();
        //*** Cria um objeto novoCurso e carrega as informações */
        Curso novoCurso = new Curso(0, nome, duracao);
        //*** Manda o objeto para o repositório para ser salvo
        repoCurso.salvar(novoCurso);

        System.out.println("Novo curso adicionado com sucesso!");

    }

    public void listar(){
        //** busca no repositorio a lista de cursos cadastrados */
        listaCursos = repoCurso.listarTodos();

        // testa se a lista esta vazia, se estiver sai do método
        if(listaCursos.isEmpty()){
            System.out.println("Lista Vazia!");
            return;
        }

        System.out.println("\n***** Lista de cursos cadastrados *****");
        // Este for intera na lista de objetos e carrega o objeto
        // na variável c
        for(Curso c: listaCursos){
            System.out.println(c);
        }

        System.out.println("Total: " + listaCursos.size() + " cursos");
    }

    public void atualizar(){
        //** Carrega a lista do "BD" */
        listar();
        /** sai do método se a lista for vazia */
        if (listaCursos.isEmpty())
            return;
        
        /*** pega o id do curso que deseja atualizar */
        System.out.println("Digite o ID do curso para atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Curso cursoSelecionado = null; //cria um curso temporário
        /*** percorre a lista de cursos até encontrar o ID que deseja atualizar */
        for(Curso c : listaCursos){
            if(c.getId() == id){
                cursoSelecionado = c;
                break;
            }
        }

        /*** retorna mensagem ao usuário caso não encontre o ID */
        if (cursoSelecionado == null){
            System.out.println("Curso não encotrado!");
            return;
        }

        /***  atualiza os campos do curso */
        System.out.println("\n --- Atualizando curso: " + 
                            cursoSelecionado.getNome());
        
        System.out.println("Novo nome (Enter para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()){
            cursoSelecionado.setNome(nome);
        }

        System.out.println("Nova duração (0 para manter): ");
        int duracao = scanner.nextInt();
        if (duracao > 0){
            cursoSelecionado.setDuracao(duracao);
        }

        /***  salva as alterações aplicadas */
        //repoCurso.salvar(cursoSelecionado);

        scanner.nextLine();

        System.out.println("Curso atualizado com sucesso!");
    }

    public void buscarPorNome(){
        listaCursos = repoCurso.listarTodos();

        System.out.println("\nDigite o nome para a busca: ");
        String buscaNome = scanner.nextLine().toLowerCase();

        List<Curso> resultados = new ArrayList<>();
        for(Curso c : listaCursos){
            if(c.getNome().toLowerCase().contains(buscaNome)){
                resultados.add(c);
            }
        }

        if(resultados.isEmpty())
            System.out.println("Nenhum curso encontrado!!!");
        else{
            System.out.println("\n=== Resultado da busca ===");
            for(Curso c : resultados)
                System.out.println(c);

            System.out.println("Encontrados "+ resultados.size()
                    + " cursos");
        }

    }

}
