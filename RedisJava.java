package dao;

import java.util.*;
import redis.clients.jedis.Jedis;

/**
 * 
 * Classe demonstrativa de utiliza��o do Redis (um banco de dados NoSQL
 * chave-valor escrito em C, cujo o armazenamento � feito na mem�ria).
 * 
 * Depend�ncia necess�ria no arquivo pom.xml: <dependencies> <dependency>
 * <groupId>redis.clients</groupId> <artifactId>jedis</artifactId>
 * <version>2.9.0</version> </dependency> </dependencies>
 * 
 * Exemplo testado com uso do Redis na vers�o 2.4.5. Antes do c�digo ser
 * executado, � necess�rio que o server e o cliente do Redis estejam em
 * execu��o.
 * 
 * @author Rafael.Valle
 *
 */
public class RedisJava {

	public static void main(String[] args) {

		// Conectando no Redis do localhost
		Jedis jedis = new Jedis("localhost");

		// Testa se o servidor est� executando e Retorna "PONG" no caso de sucesso
		System.out.println("Servidor executando... " + jedis.ping());

		// Insere os dados do tipo String ("Chave", "Valor")
		jedis.set("Chave", "Exemplo de valor String inserido");

		// Recupera String com � partir da chave
		String chave = jedis.get("Chave");

		// Exibe o valor recuperado na tela
		System.out.println("Valor guardado na chave: " + chave);

		// Insere os dados em formato List<String> no server"
		System.out.println("Lista:");
		jedis.lpush("Lista", "- Item01");
		jedis.lpush("Lista", "- Item02");
		jedis.lpush("Lista", "- Item03");
		jedis.lpush("Lista", "- Item04");
		jedis.lpush("Lista", "- Item05");
		jedis.lpush("Lista", "- Item06");
		jedis.lpush("Lista", "- Item07");
		jedis.lpush("Lista", "- Item08");
		jedis.lpush("Lista", "- Item09");

		// Recupera dados de uma lista de Strings do servidor
		// lrange recebe os par�metros: nome da lista, �ndice inicial e �ndice final.
		List<String> list = jedis.lrange("Lista", 0, 100);

		// Exibe os dados da lista recuperada na tela
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Valor guardado na lista : " + list.get(i));
		}

		//M�todos �teis para limpar o banco de dados:
		// limpar server jedis.flushall(); : Limpa todos os bancos de dados em execu��o
		// limpar server jedis.flushdb(); : Limpa apenas o banco de dados atual

		// Fecha conex�o
		jedis.close();
	}
}