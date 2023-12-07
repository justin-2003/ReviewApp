package com.example.reviewapp;

public class reviewClass {

        private String title;
        private String description;

        public reviewClass() {
           title = "";
           description  = "";
        }

        public reviewClass(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String text) {
            this.description = text;
        }

}
