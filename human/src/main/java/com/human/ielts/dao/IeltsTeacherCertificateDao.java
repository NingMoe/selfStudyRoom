package com.human.ielts.dao;

import com.human.ielts.entity.IeltsTeacherCertificate;

public interface IeltsTeacherCertificateDao {
    int deleteByPrimaryKey(Integer teacher_id);

    int insert(IeltsTeacherCertificate record);

    int insertSelective(IeltsTeacherCertificate record);

    IeltsTeacherCertificate selectByPrimaryKey(Integer teacher_id);

    int updateByPrimaryKeySelective(IeltsTeacherCertificate record);

    int updateByPrimaryKey(IeltsTeacherCertificate record);

    public IeltsTeacherCertificate selectByEmailaddr(String emailAddr);

    /**
     * 查询Celtaz证书人数
     * @return
     */
    public Integer selectceltazcount();

    /**
     * 查询教师资格证人数
     * @return
     */
    public Integer selectcertificatecount();

    public Integer updateByTeacherId(IeltsTeacherCertificate ieltsTeacherCertificate);
}