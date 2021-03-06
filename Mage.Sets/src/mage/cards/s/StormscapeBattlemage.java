
package mage.cards.s;

import java.util.UUID;
import mage.MageInt;
import mage.ObjectColor;
import mage.abilities.TriggeredAbility;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.condition.common.KickedCostCondition;
import mage.abilities.decorator.ConditionalTriggeredAbility;
import mage.abilities.effects.common.DestroyTargetEffect;
import mage.abilities.effects.common.GainLifeEffect;
import mage.abilities.keyword.KickerAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.target.common.TargetCreaturePermanent;

/**
 *
 * @author LevelX2
 */
public final class StormscapeBattlemage extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("nonblack creature");
    static {
        filter.add(Predicates.not(new ColorPredicate(ObjectColor.BLACK)));
    }

    public StormscapeBattlemage(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{2}{U}");
        this.subtype.add(SubType.METATHRAN);
        this.subtype.add(SubType.WIZARD);

        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Kicker {W} and/or {2}{B}
        KickerAbility kickerAbility = new KickerAbility("{W}");
        kickerAbility.addKickerCost("{2}{B}");
        this.addAbility(kickerAbility);

        // When Stormscape Battlemage enters the battlefield, if it was kicked with its {W} kicker, you gain 3 life.
        this.addAbility(new ConditionalTriggeredAbility(
                new EntersBattlefieldTriggeredAbility(new GainLifeEffect(3),false),
                new KickedCostCondition("{W}"),
                "When Stormscape Battlemage enters the battlefield, if it was kicked with its {W} kicker, you gain 3 life."));
        
        // When Stormscape Battlemage enters the battlefield, if it was kicked with its {2}{B} kicker, destroy target nonblack creature. That creature can't be regenerated.        
        TriggeredAbility ability = new EntersBattlefieldTriggeredAbility(new DestroyTargetEffect(true),false);
        ability.addTarget(new TargetCreaturePermanent(filter));
        this.addAbility(new ConditionalTriggeredAbility(
                ability, new KickedCostCondition("{2}{B}"),
                "When Stormscape Battlemage enters the battlefield, if it was kicked with its {2}{B} kicker, destroy target nonblack creature. That creature can't be regenerated."));        
    }

    public StormscapeBattlemage(final StormscapeBattlemage card) {
        super(card);
    }

    @Override
    public StormscapeBattlemage copy() {
        return new StormscapeBattlemage(this);
    }
}
