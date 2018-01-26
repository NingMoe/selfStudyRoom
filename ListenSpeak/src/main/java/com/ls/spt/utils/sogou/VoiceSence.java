package com.ls.spt.utils.sogou;

public class VoiceSence {
    /**
     * 国际移动设备身份证码的编写
     */
    private String imei_no;
    
    /**
     * 00000预留字段
     * 000表示不压缩  001为speex压缩 010为adpcm压缩 011为amr压缩 默认为001
     * 11010
     * 0001
     * 00未知
     * 000
     */
    private long type_no;
    
    /**
     * 默认0
     */
    private int area;
    
    /**
     * 置为空
     */
    private String base_no;
    
    /**
     * 时间戳
     */
    private long start_time;
    
    /**
     * 1,2....-n
     */
    private int sequence_no;
    
    /**
     * 语音片段的长度
     */
    private int voice_length;
    
    /**
     * 结果条数
     */
    private int result_amount;
    
    /**
     * 是否取消。默认为0
     */
    private int cancel;
    
    /**
     * 语音片段
     */
    private byte[] voice_content;
    
    /**
     * 版本
     */
    private int v;
    

    public String getImei_no() {
        return imei_no;
    }

    public void setImei_no(String imei_no) {
        this.imei_no = imei_no;
    }

    public long getType_no() {
        return type_no;
    }

    public void setType_no(long type_no) {
        this.type_no = type_no;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getBase_no() {
        return base_no;
    }

    public void setBase_no(String base_no) {
        this.base_no = base_no;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public int getSequence_no() {
        return sequence_no;
    }

    public void setSequence_no(int sequence_no) {
        this.sequence_no = sequence_no;
    }

    public int getVoice_length() {
        return voice_length;
    }

    public void setVoice_length(int voice_length) {
        this.voice_length = voice_length;
    }

    public int getResult_amount() {
        return result_amount;
    }

    public void setResult_amount(int result_amount) {
        this.result_amount = result_amount;
    }

    public int getCancel() {
        return cancel;
    }

    public void setCancel(int cancel) {
        this.cancel = cancel;
    }

    public byte[] getVoice_content() {
        return voice_content;
    }

    public void setVoice_content(byte[] voice_content) {
        this.voice_content = voice_content;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
    
}
