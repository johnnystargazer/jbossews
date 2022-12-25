package com.johnny.boot.openshift.util.data;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.google.common.collect.Lists;

public class DynamicSpecifications {

	public static <T> Specification<T> bySearchFilter(
			final Collection<SearchFilter> filters, final Class<T> entityClazz) {
		return new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
					CriteriaBuilder builder) {
				if (Collections3.isNotEmpty(filters)) {

					List<Predicate> predicates = Lists.newArrayList();
					for (SearchFilter filter : filters) {
						String[] names = StringUtils.split(filter.field, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}

						// logic operator
						switch (filter.op) {
						case eq:
							predicates.add(builder.equal(expression,
									filter.data));
							break;
						case cn:
							predicates.add(builder.like(expression, "%"
									+ filter.data + "%"));
							break;
						case gt:
							predicates.add(builder.greaterThan(expression,
									(Comparable) filter.data));
							break;
						case lt:
							predicates.add(builder.lessThan(expression,
									(Comparable) filter.data));
							break;
						case ge:
							predicates.add(builder.greaterThanOrEqualTo(
									expression, (Comparable) filter.data));
							break;
						case le:
							predicates.add(builder.lessThanOrEqualTo(
									expression, (Comparable) filter.data));
							break;
						}
					}

					// 将所有条件用 and 联合起来
					if (!predicates.isEmpty()) {
						return builder.and(predicates
								.toArray(new Predicate[predicates.size()]));
					}
				}

				return builder.conjunction();
			}
		};
	}
}
