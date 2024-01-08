package HakSa;

public class Student {

   private String stdID; // 학번
   private String stdName; // 이름
   private String stdMajor; // 전공
   private String stdGender; // 성별
   private Integer stdAge; // 나이
   private String stdAddress; // 주소
   private String stdStatus; // 재적 상태

   private Double std11; // 학점(1-1)
   private Double std12; // 학점(1-2)
   private Double std21; // 학점(2-1)
   private Double std22; // 학점(2-2)
   private Double std31; // 학점(3-1)
   private Double std32; // 학점(3-2)
   private Double std41; // 학점(4-1)
   private Double std42; // 학점(4-2)

   private Double stdGradeAvg; // 평균

   public Student(String stdID, String stdName, String stdMajor, String stdGender, Integer stdAge, String stdAddress,
         String stdStatus/*
                      * , Double std11, Double std12, Double std21, Double std22, Double std31,
                      * Double std32, Double std41, Double std42
                      */) {
      this.stdID = stdID;
      this.stdName = stdName;
      this.stdMajor = stdMajor;
      this.stdGender = stdGender;
      this.stdAge = stdAge;
      this.stdAddress = stdAddress;
      this.stdStatus = stdStatus;
   }

   public Student(String stdID, String stdName, Double std11, Double std12, Double std21, Double std22, Double std31,
         Double std32, Double std41, Double std42, Double stdGradeAvg) {
      this.stdID = stdID;
      this.stdName = stdName;
      this.std11 = std11;
      this.std12 = std12;
      this.std21 = std21;
      this.std22 = std22;
      this.std31 = std31;
      this.std32 = std32;
      this.std41 = std41;
      this.std42 = std42;
      this.stdGradeAvg = stdGradeAvg;
   }

   public String getStdID() {
      return stdID;
   }

   public void setStdID(String stdID) {
      this.stdID = stdID;
   }

   public String getStdName() {
      return stdName;
   }

   public void setStdName(String stdName) {
      this.stdName = stdName;
   }

   public String getStdMajor() {
      return stdMajor;
   }

   public void setStdMajor(String stdMajor) {
      this.stdMajor = stdMajor;
   }

   public String getStdGender() {
      return stdGender;
   }

   public void setStdGender(String stdGender) {
      this.stdGender = stdGender;
   }

   public Integer getStdAge() {
      return stdAge;
   }

   public void setStdAge(Integer stdAge) {
      this.stdAge = stdAge;
   }

   public String getStdAddress() {
      return stdAddress;
   }

   public void setStdAddress(String stdAddress) {
      this.stdAddress = stdAddress;
   }

   public String getStdStatus() {
      return stdStatus;
   }

   public void setStdStatus(String stdStatus) {
      this.stdStatus = stdStatus;
   }

   public Double getStd11() {
      return std11;
   }

   public void setStd11(Double std11) {
      this.std11 = std11;
   }

   public Double getStd12() {
      return std12;
   }

   public void setStd12(Double std12) {
      this.std12 = std12;
   }

   public Double getStd21() {
      return std21;
   }

   public void setStd21(Double std21) {
      this.std21 = std21;
   }

   public Double getStd22() {
      return std22;
   }

   public void setStd22(Double std22) {
      this.std22 = std22;
   }

   public Double getStd31() {
      return std31;
   }

   public void setStd31(Double std31) {
      this.std31 = std31;
   }

   public Double getStd32() {
      return std32;
   }

   public void setStd32(Double std32) {
      this.std32 = std32;
   }

   public Double getStd41() {
      return std41;
   }

   public void setStd41(Double std41) {
      this.std41 = std41;
   }

   public Double getStd42() {
      return std42;
   }

   public void setStd42(Double std42) {
      this.std42 = std42;
   }

   public Double getStdGradeAvg() {
      return stdGradeAvg;
   }

   public void setStdGradeAvg(Double stdGradeAvg) {
      this.stdGradeAvg = stdGradeAvg;
   }

}