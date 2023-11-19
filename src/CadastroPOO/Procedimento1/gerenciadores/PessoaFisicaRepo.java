package CadastroPOO.Procedimento1.gerenciadores;

import CadastroPOO.Procedimento1.model.PessoaFisica;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> pessoasFisicas = new ArrayList();

    public PessoaFisicaRepo() {
    }

    public void inserir(PessoaFisica pessoaFisica) {
        this.pessoasFisicas.add(pessoaFisica);
    }

    public void alterar(PessoaFisica pessoaFisica, String novoNome, String novoCpf, int novaIdade) {
        pessoaFisica.setNome(novoNome);
        pessoaFisica.setCpf(novoCpf);
        pessoaFisica.setIdade(novaIdade);
    }

    public void excluir(int id) {
        this.pessoasFisicas.remove(this.obter(id));
    }

    public PessoaFisica obter(int id) throws NoSuchElementException {
        Optional<PessoaFisica> pessoaFisicaEncontrada = this.pessoasFisicas.stream().filter((pessoaFisica) -> {
            return pessoaFisica.getId() == id;
        }).findFirst();
        if (pessoaFisicaEncontrada.isPresent()) {
            return (PessoaFisica)pessoaFisicaEncontrada.get();
        } else {
            throw new NoSuchElementException("Pessoa f\u00edsica com ID " + id + " n\u00e3o encontrada.");
        }
    }

    public ArrayList<PessoaFisica> obterTodos() {
        return this.pessoasFisicas;
    }

    public void persistir(String nomeArquivo) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
        outputStream.writeObject(this.pessoasFisicas);
        outputStream.close();
        System.out.println("Dados da pessoa f\u00edsica armazenados.");
        System.out.println();
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo));
        this.pessoasFisicas = (ArrayList)inputStream.readObject();
        inputStream.close();
        System.out.println("Dados da pessoa f\u00edsica recuperados.");
        System.out.println();
    }
}

