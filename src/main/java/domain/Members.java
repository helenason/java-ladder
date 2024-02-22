package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Members {

    private static final int MIN_MEMBER_COUNT = 2;
    private static final int MAX_MEMBER_COUNT = 15;

    private final List<Member> members = new ArrayList<>();

    public Members(String rawNames) {
        validate(rawNames);
    }

    private void validate(String rawNames) {
        validateNull(rawNames);
        List<String> names = addMembers(rawNames);
        validateDuplication(names);
        validateCount(names);
        names.forEach(name -> members.add(new Member(name)));
    }

    private List<String> addMembers(String rawNames) {
        return Arrays.stream(rawNames.split(",", -1))
                .map(String::trim)
                .toList();
    }

    private void validateVer2(String rawNames) {
        validateNull(rawNames);
        List<String> names = addMembersVer2(rawNames);
        validateDuplication(names);
        validateCount(names);
    }

    private List<String> addMembersVer2(String rawNames) {
        return Arrays.stream(rawNames.split(","))
                .map(name -> {
                    String trimmedName = name.trim();
                    members.add(new Member(trimmedName));
                    return trimmedName;
                }).toList();
    }

    private void validateNull(String rawNames) {
        if (rawNames == null) {
            throw new IllegalArgumentException("null을 입력할 수 없습니다.");
        }
    }

    private void validateDuplication(List<String> names) {
        Set<String> nonDuplicated = new HashSet<>(names);
        if (names.size() != nonDuplicated.size()) {
            throw new IllegalArgumentException("이름은 서로 중복될 수 없습니다.");
        }
    }

    private void validateCount(List<String> names) {
        if (names.size() < MIN_MEMBER_COUNT || names.size() > MAX_MEMBER_COUNT) {
            throw new IllegalArgumentException("참여자는 " + MIN_MEMBER_COUNT + "~" + MAX_MEMBER_COUNT + "명만 허용됩니다.");
        }
    }
}