
/*
 * All rights Reserved, Designed By zhongj
 * @Title: User2.java
 * @Package cn.featherfly.common.dozer
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-03-12 13:50:12
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.dozer;

import java.time.LocalDate;

/**
 * User2.
 *
 * @author zhongj
 */
public class User2 {

    private Long id;

    private String name;

    private Integer age;

    private LocalDate birthday;

    /**
     * get id value
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * set id value
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get name value
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set name value
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get age value
     *
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * set age value
     *
     * @param age age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Getter for property 'birthday'.
     *
     * @return Value for property 'birthday'.
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Setter for property 'birthday'.
     *
     * @param birthday Value to set for property 'birthday'.
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "User2{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", birthday=" + birthday + '}';
    }
}
