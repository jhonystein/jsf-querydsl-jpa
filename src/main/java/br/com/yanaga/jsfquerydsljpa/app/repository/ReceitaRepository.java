package br.com.yanaga.jsfquerydsljpa.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.yanaga.jsfquerydsljpa.app.QReceita;
import br.com.yanaga.jsfquerydsljpa.app.QTipo;
import br.com.yanaga.jsfquerydsljpa.app.Receita;
import br.com.yanaga.jsfquerydsljpa.app.filtro.FiltroReceita;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class ReceitaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Receita> filtrar(FiltroReceita filtro) {
		JPQLQuery query = new JPAQuery(entityManager);
		QReceita qReceita = QReceita.receita;
		QTipo qTipo = QTipo.tipo;
		return query.from(qReceita).leftJoin(qReceita.tipos, qTipo).fetch().where(filtro.toPredicate())
				.listDistinct(qReceita);
	}

}
