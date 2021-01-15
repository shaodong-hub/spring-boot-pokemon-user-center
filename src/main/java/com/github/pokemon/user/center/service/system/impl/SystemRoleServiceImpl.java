package com.github.pokemon.user.center.service.system.impl;

import com.github.pokemon.user.center.pojo.dto.SimpleRoleDTO;
import com.github.pokemon.user.center.pojo.dto.SystemRoleQueryDTO;
import com.github.pokemon.user.center.pojo.entity.SystemRoleDO;
import com.github.pokemon.user.center.pojo.vo.ISimpleRoleVO;
import com.github.pokemon.user.center.repository.ISystemRoleRepository;
import com.github.pokemon.user.center.service.system.ISystemRoleService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class SystemRoleServiceImpl implements ISystemRoleService<ISimpleRoleVO, SimpleRoleDTO> {

    private final ISystemRoleRepository repository;

    @Override
    public Page<SystemRoleDO> findAllRoles(SystemRoleQueryDTO condition, Pageable pageable) {
        return repository.findAll(specification(condition), pageable);
    }

    @Override
    public void createRole(SimpleRoleDTO role) {

    }

    @Override
    public void updateRole(SimpleRoleDTO role) {

    }

    @Override
    public void deleteRoleById(long roleId) {

    }

    /**
     * 动态拼接查询条件
     *
     * @param condition 查询条件
     * @return Specification
     */
    private Specification<SystemRoleDO> specification(SystemRoleQueryDTO condition) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = Lists.newArrayList();
            roleName(root, criteriaBuilder, list, condition.getRoleName());
            authority(root, criteriaBuilder, list, condition.getAuthority());
            Predicate[] predicates = list.toArray(new Predicate[0]);
            return criteriaQuery.where(predicates).getRestriction();
        };
    }

    private void roleName(Root<SystemRoleDO> root, CriteriaBuilder builder, List<Predicate> list, String username) {
        if (StringUtils.isNotBlank(username)) {
            Predicate predicateParent = builder.equal(root.get("username").as(username.getClass()), username);
            list.add(predicateParent);
        }
    }

    private void authority(Root<SystemRoleDO> root, CriteriaBuilder builder, List<Predicate> list, String authority) {
        if (StringUtils.isNotBlank(authority)) {
            Predicate predicateParent = builder.equal(root.get("authority").as(authority.getClass()), authority);
            list.add(predicateParent);
        }
    }

}
