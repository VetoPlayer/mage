
package mage.cards.d;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.DiesTriggeredAbility;
import mage.abilities.effects.common.ReturnFromGraveyardToHandTargetEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.common.FilterCreatureCard;
import mage.filter.predicate.mageobject.AnotherCardPredicate;
import mage.target.common.TargetCardInYourGraveyard;

/**
 *
 * @author LevelX2
 */
public final class DutifulAttendant extends CardImpl {

    private static final FilterCreatureCard filter = new FilterCreatureCard("another target creature card from your graveyard");

    static {
        filter.add(new AnotherCardPredicate());
    }

    public DutifulAttendant(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{2}{B}");
        this.subtype.add(SubType.HUMAN);
        this.subtype.add(SubType.WARRIOR);
        this.power = new MageInt(1);
        this.toughness = new MageInt(2);

        // When Dutiful Ateendant dies, return another target creature card from your graveyard to your hand.
        Ability ability = new DiesTriggeredAbility(new ReturnFromGraveyardToHandTargetEffect(), false);
        ability.addTarget(new TargetCardInYourGraveyard(filter));
        this.addAbility(ability);
    }

    public DutifulAttendant(final DutifulAttendant card) {
        super(card);
    }

    @Override
    public DutifulAttendant copy() {
        return new DutifulAttendant(this);
    }
}
