package klono.mods.impl;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;

import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.world.WorldSettings.GameType;

public class PlayerComparator implements Comparator<NetworkPlayerInfo> {

	@Override
	public int compare(NetworkPlayerInfo o1, NetworkPlayerInfo o2) {
		ScorePlayerTeam team1 = o1.getPlayerTeam();
		ScorePlayerTeam team2 = o2.getPlayerTeam();
		return ComparisonChain.start().compareTrueFirst(o1.getGameType() != GameType.SPECTATOR, o2.getGameType() != GameType.SPECTATOR).compare(team1 != null ? team1.getTeamName() : "", team2 != null ? team2.getTeamName() : "").compare(o1.getGameProfile().getName(), o2.getGameProfile().getName()).result();
	}

}
