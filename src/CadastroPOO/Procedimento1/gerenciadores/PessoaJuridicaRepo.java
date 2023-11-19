package CadastroPOO.Procedimento1.gerenciadores;

import CadastroPOO.Procedimento1.model.PessoaJuridica;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> pessoasJuridicas = new ArrayList();

    public PessoaJuridicaRepo() {
    }

    public void inserir(PessoaJuridica pessoaJuridica) {
        this.pessoasJuridicas.add(pessoaJuridica);
    }

    public void alterar(PessoaJuridica pessoaJuridica, String novoNome, String novoCpf) {
        pessoaJuridica.setNome(novoNome);
        pessoaJuridica.setCnpj(novoCpf);
    }

    public void excluir(int id) {
        this.pessoasJuridicas.remove(this.obter(id));
    }

    public PessoaJuridica obter(int id) throws NoSuchElementException {
        Optional<PessoaJuridica> pessoaJuridicaEncontrada = this.pessoasJuridicas.stream().filter((pessoaJuridica) -> {
            return pessoaJuridica.getId() == id;
        }).findFirst();
        if (pessoaJuridicaEncontrada.isPresent()) {
            return (PessoaJuridica)pessoaJuridicaEncontrada.get();
        } else {
            throw new NoSuchElementException("Pessoa jur\u00eddica com ID " + id + " n\u00e3o encontrada.");
        }
    }

    public ArrayList<PessoaJuridica> obterTodos() {
        return this.pessoasJuridicas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
        outputStream.writeObject(this.pessoasJuridicas);
        outputStream.close();
        System.out.println("Dados da pessoa jur\u00eddica armazenados.");
        System.out.println();
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo));
        this.pessoasJuridicas = (ArrayList)inputStream.readObject();
        inputStream.close();
        System.out.println("Dados da pessoa jur\u00eddica recuperados.");
        System.out.println();
    }
}
