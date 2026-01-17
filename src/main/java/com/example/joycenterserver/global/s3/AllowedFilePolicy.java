package com.example.joycenterserver.global.s3;

import java.util.Set;

public class AllowedFilePolicy {

    private static final Set<String> IMAGE_EXT = Set.of("jpg", "jpeg", "png", "webp");
    private static final Set<String> VIDEO_EXT = Set.of("mp4", "webm");
    private static final Set<String> FILE_EXT = Set.of("pdf", "hwp", "zip", "docx");

    public static boolean isAllowed(String ext, String mime, String type) {
        return switch (type) {
            case "IMAGE" -> IMAGE_EXT.contains(ext) && mime.startsWith("image/");
            case "VIDEO" -> VIDEO_EXT.contains(ext) && mime.startsWith("video/");
            case "FILE" -> FILE_EXT.contains(ext);
            default -> false;
        };
    }
}
