package com.monkey_company.monkey_health.global.translation;

import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    public String translateToEnglish(String koreanText) {
        if (koreanText == null) {
            return null;
        }

        switch (koreanText) {
            case "가슴":
                return "Chest";
            case "등":
                return "Back";
            case "하체":
                return "Legs";
            case "복근":
                return "Abs";
            case "전신":
                return "Body";
            default:
                return koreanText;
        }
    }

    public String translateToKorean(String englishText) {
        if (englishText == null) {
            return null;
        }

        switch (englishText) {
            case "Chest":
                return "가슴";
            case "Back":
                return "등";
            case "Legs":
                return "하체";
            case "Abs":
                return "복근";
            case "Body":
                return "전신";
            default:
                return englishText;
        }
    }
}
