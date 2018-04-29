//计划:当玩家输入/tgame join 时加入游戏
//输入/tgame quit时退出游戏
//有两个队,1v1
//杀一个人得一分


import com.locydragon.gamelib.api.CustomGame;
import com.locydragon.gamelib.api.GameLibrary;
import com.locydragon.gamelib.api.entity.PlayingPlayer;
import com.locydragon.gamelib.api.event.baby.bukkitevents.GamePlayerDamageEntityEvent;
import com.locydragon.gamelib.api.event.baby.bukkitevents.GamePlayerDeathEvent;
import com.locydragon.gamelib.api.event.type.EventType;
import com.locydragon.gamelib.api.util.CommandManager;
import com.locydragon.gamelib.api.util.ScoreManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author LocyDragon
 * This is only a example!You can use this code anyway!
 */
public class TestGame extends JavaPlugin {
	CustomGame game = null;
	//游戏
	ScoreManager manager = null;
	//积分器
	@Override
	public void onEnable() {
		game = GameLibrary.getLibrary().setMaxPlayerInOneTeam(1)
				//设置每队最多的人数
				.teamNumber(2)
				//设置队数量
				.build()
				//生成Game实例对象
				.addEvent(EventType.PLAYING_PLAYER_DEATH, gameEvent -> {
					    GamePlayerDeathEvent event = (GamePlayerDeathEvent)gameEvent;
					    //这里可以强转
						PlayingPlayer player = PlayingPlayer.search(event.getProp().getEntity().getKiller());
					    //获取PlayingPlayer实例对象
					    if (player.getGame() == null || player.getTeam() == -1) {
					    	//检查一下杀人者是否在游戏当中
					    	return;
						}
					    manager.addOneScore(player.getTeam());
					    //加一分
						game.broadcastMsg(ChatColor.RED+player.getPlayerName()+"杀死了"+event.getProp().getEntity().getName());
						//广播杀敌信息
						game.broadcastMsg(ChatColor.RED+"目前组"+event.getPlayer().getTeam()+"分数: "+manager.getScore(player.getTeam()));
						//广播分数
				})
				.addEvent(EventType.ON_GAME_JOIN, gameEvent -> {
					((Player)gameEvent.getPlayer()).sendMessage("你进入了游戏.");
					//凡是以"ON"开头的事件都得转成Player，其他转成PlayingPlayer即可
				})
				.addEvent(EventType.ON_GAME_QUIT, gameEvent -> {
					((Player)gameEvent.getPlayer()).sendMessage("你退出了游戏.");
					//凡是以"ON"开头的事件都得转成Player，其他转成PlayingPlayer即可
				})
				.startGame();
		manager = new ScoreManager(game);
		//实例化积分器对象
		new CommandManager("tgame")
				.bindJoinCmd("join", this.game)
				//快速加入游戏指令，不过不推荐这么做
				.bindQuitCmd("quit", this.game);
		        //快速设置退出游戏指令
	}
}
