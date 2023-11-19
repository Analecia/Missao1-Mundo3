package CadastroPOO.Procedimento1;

import CadastroPOO.Procedimento1.model.PessoaFisica;
import CadastroPOO.Procedimento1.model.PessoaJuridica;
import CadastroPOO.Procedimento1.gerenciadores.PessoaFisicaRepo;
import CadastroPOO.Procedimento1.gerenciadores.PessoaJuridicaRepo;
import java.io.IOException;
public class App {
    public App() {
    }

    public static void main(String[] args) {
        PessoaFisicaRepo pessoaFisicaRepo1 = new PessoaFisicaRepo();
        PessoaFisica pessoaFisica1 = new PessoaFisica(1, "Maribel", "12.123.123-12", 21);
        PessoaFisica pessoaFisica2 = new PessoaFisica(2, "Lucca", "34.345.345-34",27);
        pessoaFisicaRepo1.inserir(pessoaFisica1);
        pessoaFisicaRepo1.inserir(pessoaFisica2);

        try {
            pessoaFisicaRepo1.persistir("pessoas_fisicas.dat");
            PessoaFisicaRepo pessoaFisicaRepo2 = new PessoaFisicaRepo();
            pessoaFisicaRepo2.recuperar("pessoas_fisicas.dat");
            pessoaFisicaRepo2.obterTodos().forEach((pessoaFisica) -> {
                pessoaFisica.exibir();
                System.out.println();
            });
        }

        catch (ClassNotFoundException | IOException var9) {
            System.out.println("Erro ao persistir ou recuperar os dados: " + var9.getMessage());
        }

        PessoaJuridicaRepo pessoaJuridicaRepo1 = new PessoaJuridicaRepo();
        PessoaJuridica pessoaJuridica1 = new PessoaJuridica(1, "Comercial Casa da Cesta", "11.111.111/0000-00");
        PessoaJuridica pessoaJuridica2 = new PessoaJuridica(2, "Supermercado Oliveira", "00.000.000/1111-11");
        pessoaJuridicaRepo1.inserir(pessoaJuridica1);
        pessoaJuridicaRepo1.inserir(pessoaJuridica2);

        try {
            pessoaJuridicaRepo1.persistir("pessoas_juridicas.dat");
            PessoaJuridicaRepo pessoaJuridicaRepo2 = new PessoaJuridicaRepo();
            pessoaJuridicaRepo2.recuperar("pessoas_juridicas.dat");
            pessoaJuridicaRepo2.obterTodos().stream().forEach((pessoaJuridica) -> {
                pessoaJuridica.exibir();
                System.out.println();
            });
        } catch (ClassNotFoundException | IOException var8) {
            System.out.println("Erro ao persistir ou recuperar os dados: " + var8.getMessage());
        }

    }
}