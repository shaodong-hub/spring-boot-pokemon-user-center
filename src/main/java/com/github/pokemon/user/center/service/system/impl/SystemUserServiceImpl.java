package com.github.pokemon.user.center.service.system.impl;

import com.github.pokemon.user.center.pojo.dto.SystemUserQueryDTO;
import com.github.pokemon.user.center.pojo.entity.SystemUserDO;
import com.github.pokemon.user.center.pojo.vo.ISimpleUserVO;
import com.github.pokemon.user.center.repository.ISystemUserRepository;
import com.github.pokemon.user.center.service.system.ISystemUserService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

/**
 * TODO
 * <p>
 * create in 2021/1/14 9:13 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl implements ISystemUserService<ISimpleUserVO> {

    private final ISystemUserRepository repository;

    @Override
    public Page<SystemUserDO> listUsers(SystemUserQueryDTO condition, Pageable pageable) {
        return repository.findAll(specification(condition), pageable);
    }

    /**
     * 动态拼接查询条件
     *
     * @param condition 查询条件
     * @return Specification
     */
    private Specification<SystemUserDO> specification(SystemUserQueryDTO condition) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = Lists.newArrayList();
            username(root, criteriaBuilder, list, condition.getUsername());
            lastLoginDate(root, criteriaBuilder, list, condition.getLastLoginDate());
            Predicate[] predicates = list.toArray(new Predicate[0]);
            return criteriaQuery.where(predicates).getRestriction();
        };
    }

    private void username(Root<SystemUserDO> root, CriteriaBuilder builder, List<Predicate> list, String username) {
        if (StringUtils.isNotBlank(username)) {
            Predicate predicateParent = builder.equal(root.get("username").as(username.getClass()), username);
            list.add(predicateParent);
        }
    }

    private void lastLoginDate(Root<SystemUserDO> root, CriteriaBuilder builder, List<Predicate> list, LocalDate lastLoginDate) {
        if (!ObjectUtils.isEmpty(lastLoginDate)) {
            Predicate predicateParent = builder.greaterThan(root.get("lastLoginDate").as(lastLoginDate.getClass()), lastLoginDate);
            list.add(predicateParent);
        }
    }

}
