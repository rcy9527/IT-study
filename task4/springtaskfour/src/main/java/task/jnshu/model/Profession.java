package task.jnshu.model;public class Profession {    private Integer id;    private String profession;    private Long createAt;    private Long updateAt;    private Integer socialNeeds;    private Integer learningFront;    private Integer difficult;    private String images;    private String introduction;    private String prospect;    public Integer getId() {        return id;    }    public void setId(Integer id) {        this.id = id;    }    public String getProfession() {        return profession;    }    public void setProfession(String profession) {        this.profession = profession; //== null ? null : profession.trim();    }    public Long getCreateAt() {        return createAt;    }    public void setCreateAt(Long createAt) {        this.createAt = createAt;    }    public Long getUpdateAt() {        return updateAt;    }    public void setUpdateAt(Long updateAt) {        this.updateAt = updateAt;    }    public Integer getSocialNeeds() {        return socialNeeds;    }    public void setSocialNeeds(Integer socialNeeds) {        this.socialNeeds = socialNeeds;    }    public Integer getLearningFront() {        return learningFront;    }    public void setLearningFront(Integer learningFront) {        this.learningFront = learningFront;    }    public Integer getDifficult() {        return difficult;    }    public void setDifficult(Integer difficult) {        this.difficult = difficult;    }    public String getImages() {        return images;    }    public void setImages(String images) {        this.images = images;// == null ? null : images.trim();    }    public String getIntroduction() {        return introduction;    }    public void setIntroduction(String introduction) {        this.introduction = introduction; // == null ? null : introduction.trim();    }    public String getProspect() {        return prospect;    }    public void setProspect(String prospect) {        this.prospect = prospect; // == null ? null : prospect.trim();    }    @Override    public String toString() {        return  "Student [Id = " + id + ", profession = " + profession + ", socialNeeds = " + socialNeeds + ", learningFront:"  + learningFront +                ", difficult = " + difficult + ", images = " + images + ",\n\t\t introduction = " + introduction +                "\n\t\t prospect = "+ prospect + ",\n\t createAt = " + createAt + ", updateAt = " + updateAt + "]\n";    }}