package com.human.security;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MyUser implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 5485018355764818402L;
    
    private Long userid;
    
    private String password;

    private String name;// 用户的中文姓名

    private final String username;
    
    private String empId;
    /**
     * 用户类型:0,自建，1.同步
     */
    private Integer empSource;
    

    /**
     * 业务电话
     */
    private String busPhone;
    
    /**
     * 本地新建的机构名称
     */
    private String comName;
    
    private String companyId;
    
    /**
     * 公司名称
     */
    private String companyName;
    
    /**
     * 部门ID
     */
    private String deptId;
    
    /**
     * 部门名称
     */
    private String deptName;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
    private Timestamp synTime;
    
    /**
     * 邮箱
     */
    private String emailAddr;
    
    /**
     * 员工电话
     */
    private String empPhone;
    
    /**
     * 民族
     */
    private String ethic;
    
    private String graduageSch;

    private String highestEduc;
    
    private String hrStatus;
    
    private String jobCode;
    
    private String major;
    
    private String nationalId;
    
    private String nationnlDesc;
    
    private String sex;
    
    private String jobZhiji;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
    private Timestamp joinDate;
    
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
    private Timestamp birthDate;

    private Long createUser;
    
    private String createName;
    
    private final Set<GrantedAuthority> authorities;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;

    // ~ Constructors
    // ===================================================================================================
    public MyUser(Long userid, String password, String name, String empId,final String username, Integer empSource,  String busPhone, String comName,String companyId, String companyName, String deptId,
            String deptName, Timestamp synTime, String emailAddr, String empPhone, String ethic, String graduageSch, String highestEduc, String hrStatus, String jobCode, String major,
            String nationalId, String nationnlDesc, String sex, String jobZhiji, Timestamp joinDate, Timestamp birthDate, Long createUser, String createName,
            final Collection<? extends GrantedAuthority> authorities, final boolean accountNonExpired, final boolean accountNonLocked, final boolean credentialsNonExpired, final boolean enabled) {
        if (username == null || "".equals(username) || password == null)
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
     this.userid=userid;
     this.password = password;
     this.name = name;
     this.empId=empId;
     this.username = username;
     this.empSource=empSource;
     this.busPhone=busPhone;
     this.comName=comName;
     this.companyId=companyId;
     this.companyName=companyName;
     this.deptId=deptId;
     this.deptName=deptName;
     this.synTime=synTime;
     this.emailAddr=emailAddr;
     this.empPhone=empPhone;
     this.ethic=ethic;
     this.graduageSch=graduageSch;
     this.highestEduc=highestEduc;
     this.hrStatus=hrStatus;
     this.jobCode=jobCode; 
     this.major=major;
     this.nationalId=nationalId;
     this.nationnlDesc=nationnlDesc;
     this.sex=sex;
     this.jobZhiji=jobZhiji;
     this.joinDate=joinDate;
     this.birthDate=birthDate;
     this.createUser=createUser;
     this.createName=createName;
     this.accountNonExpired = accountNonExpired;
     this.accountNonLocked = accountNonLocked;
     this.credentialsNonExpired = credentialsNonExpired;
     this.enabled = enabled;
     this.authorities = Collections
       .unmodifiableSet(sortAuthorities(authorities));
    }

    // ~ Methods
    // ========================================================================================================
    public Collection<GrantedAuthority> getAuthorities() {
     return authorities;
    }

    public String getPassword() {
     return password;
    }

    public String getUsername() {
     return username;
    }


    public boolean isAccountNonExpired() {
     return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
     return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
     return credentialsNonExpired;
    }

    public boolean isEnabled() {
     return enabled;
    }

    public void eraseCredentials() {
     password = null;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(
      Collection<? extends GrantedAuthority> authorities) {
     Assert.notNull(authorities,
       "Cannot pass a null GrantedAuthority collection");
     // Ensure array iteration order is predictable (as per
     // UserDetails.getAuthorities() contract and SEC-717)
     SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(
       new AuthorityComparator());

     for (GrantedAuthority grantedAuthority : authorities) {
      Assert.notNull(grantedAuthority,
        "GrantedAuthority list cannot contain any null elements");
      sortedAuthorities.add(grantedAuthority);
     }

     return sortedAuthorities;
    }

    private static class AuthorityComparator implements
      Comparator<GrantedAuthority>, Serializable {
     /**
         * 
         */
        private static final long serialVersionUID = 1L;

    public int compare(GrantedAuthority g1, GrantedAuthority g2) {
      // Neither should ever be null as each entry is checked before
      // adding it to the set.
      // If the authority is null, it is a custom authority and should
      // precede others.
      if (g2.getAuthority() == null) {
       return -1;
      }

      if (g1.getAuthority() == null) {
       return 1;
      }

      return g1.getAuthority().compareTo(g2.getAuthority());
     }
    }

    @Override
    public boolean equals(Object rhs) {
     if (rhs instanceof MyUser)
      return username.equals(((MyUser) rhs).username);
     return false;
    }

    @Override
    public int hashCode() {
     return username.hashCode();
    }

    @Override
    public String toString() {
     StringBuilder sb = new StringBuilder();
     sb.append(super.toString()).append(": ");
     sb.append("Username: ").append(this.username).append("; ");
     sb.append("Password: [PROTECTED]; ");
     sb.append("Enabled: ").append(this.enabled).append("; ");
     sb.append("AccountNonExpired: ").append(this.accountNonExpired).append(
       "; ");
     sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired)
       .append("; ");
     sb.append("AccountNonLocked: ").append(this.accountNonLocked).append(
       "; ");

     if (!authorities.isEmpty()) {
      sb.append("Granted Authorities: ");

      boolean first = true;
      for (GrantedAuthority auth : authorities) {
       if (!first) {
        sb.append(",");
       }
       first = false;

       sb.append(auth);
      }
     } else {
      sb.append("Not granted any authorities");
     }

     return sb.toString();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getEmpSource() {
        return empSource;
    }

    public void setEmpSource(Integer empSource) {
        this.empSource = empSource;
    }


    public String getBusPhone() {
        return busPhone;
    }

    public void setBusPhone(String busPhone) {
        this.busPhone = busPhone;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Timestamp getSynTime() {
        return synTime;
    }

    public void setSynTime(Timestamp synTime) {
        this.synTime = synTime;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEthic() {
        return ethic;
    }

    public void setEthic(String ethic) {
        this.ethic = ethic;
    }

    public String getGraduageSch() {
        return graduageSch;
    }

    public void setGraduageSch(String graduageSch) {
        this.graduageSch = graduageSch;
    }

    public String getHighestEduc() {
        return highestEduc;
    }

    public void setHighestEduc(String highestEduc) {
        this.highestEduc = highestEduc;
    }

    public String getHrStatus() {
        return hrStatus;
    }

    public void setHrStatus(String hrStatus) {
        this.hrStatus = hrStatus;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getNationnlDesc() {
        return nationnlDesc;
    }

    public void setNationnlDesc(String nationnlDesc) {
        this.nationnlDesc = nationnlDesc;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJobZhiji() {
        return jobZhiji;
    }

    public void setJobZhiji(String jobZhiji) {
        this.jobZhiji = jobZhiji;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }




}
