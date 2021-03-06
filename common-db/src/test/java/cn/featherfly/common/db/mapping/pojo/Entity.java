
package cn.featherfly.common.db.mapping.pojo;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import cn.featherfly.common.db.jpa.Comment;

/**
 * <p>
 * Entity
 * </p>
 *
 * @author zhongj
 */
@Table(indexes = { @Index(name = "age_index", columnList = "age"), @Index(columnList = "username", unique = true),
        @Index(name = "state_id_index", columnList = "id,state"), })
@Comment("测试实体")
public class Entity {

    public enum State {
        ENABLE, DISABLE
    }

    @Id
    private Long id;

    @Comment("名称")
    private String name;

    private String username;

    @Column
    private Integer age;

    private String descp;

    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private LocalTime localTime;
    private Date date;
    private java.sql.Date sqlDate;
    private Timestamp timestamp;
    private Time time;
    private State state;

    private Double doubleNumber;
    private Float floatNumber;
    private BigInteger bigInteger;
    private BigDecimal bigDecimal;
    private Byte byteNumber;
    private Short shortNumber;

    /**
     * 返回id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 返回name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回age
     *
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置age
     *
     * @param age age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 返回descp
     *
     * @return descp
     */
    public String getDescp() {
        return descp;
    }

    /**
     * 设置descp
     *
     * @param descp descp
     */
    public void setDescp(String descp) {
        this.descp = descp;
    }

    /**
     * 返回localDateTime
     *
     * @return localDateTime
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * 设置localDateTime
     *
     * @param localDateTime localDateTime
     */
    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    /**
     * 返回localDate
     *
     * @return localDate
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * 设置localDate
     *
     * @param localDate localDate
     */
    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    /**
     * 返回localTime
     *
     * @return localTime
     */
    public LocalTime getLocalTime() {
        return localTime;
    }

    /**
     * 设置localTime
     *
     * @param localTime localTime
     */
    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    /**
     * 返回date
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置date
     *
     * @param date date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 返回sqlDate
     *
     * @return sqlDate
     */
    public java.sql.Date getSqlDate() {
        return sqlDate;
    }

    /**
     * 设置sqlDate
     *
     * @param sqlDate sqlDate
     */
    public void setSqlDate(java.sql.Date sqlDate) {
        this.sqlDate = sqlDate;
    }

    /**
     * 返回timestamp
     *
     * @return timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * 设置timestamp
     *
     * @param timestamp timestamp
     */
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 返回time
     *
     * @return time
     */
    public Time getTime() {
        return time;
    }

    /**
     * 设置time
     *
     * @param time time
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * 返回state
     *
     * @return state
     */
    public State getState() {
        return state;
    }

    /**
     * 设置state
     *
     * @param state state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * 返回doubleNumber
     *
     * @return doubleNumber
     */
    public Double getDoubleNumber() {
        return doubleNumber;
    }

    /**
     * 设置doubleNumber
     *
     * @param doubleNumber doubleNumber
     */
    public void setDoubleNumber(Double doubleNumber) {
        this.doubleNumber = doubleNumber;
    }

    /**
     * 返回floatNumber
     *
     * @return floatNumber
     */
    public Float getFloatNumber() {
        return floatNumber;
    }

    /**
     * 设置floatNumber
     *
     * @param floatNumber floatNumber
     */
    public void setFloatNumber(Float floatNumber) {
        this.floatNumber = floatNumber;
    }

    /**
     * 返回bigInteger
     *
     * @return bigInteger
     */
    public BigInteger getBigInteger() {
        return bigInteger;
    }

    /**
     * 设置bigInteger
     *
     * @param bigInteger bigInteger
     */
    public void setBigInteger(BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }

    /**
     * 返回bigDecimal
     *
     * @return bigDecimal
     */
    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    /**
     * 设置bigDecimal
     *
     * @param bigDecimal bigDecimal
     */
    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    /**
     * 返回byteNumber
     *
     * @return byteNumber
     */
    public Byte getByteNumber() {
        return byteNumber;
    }

    /**
     * 设置byteNumber
     *
     * @param byteNumber byteNumber
     */
    public void setByteNumber(Byte byteNumber) {
        this.byteNumber = byteNumber;
    }

    /**
     * 返回shortNumber
     *
     * @return shortNumber
     */
    public Short getShortNumber() {
        return shortNumber;
    }

    /**
     * 设置shortNumber
     *
     * @param shortNumber shortNumber
     */
    public void setShortNumber(Short shortNumber) {
        this.shortNumber = shortNumber;
    }

    /**
     * 返回username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置username
     *
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
