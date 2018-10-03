package seedu.address.model.machine;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class MachineNameContainsKeywordsPredicate implements Predicate<Machine> {
    private final List<String> keywords;

    public MachineNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Machine machine) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(machine.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MachineNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MachineNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
