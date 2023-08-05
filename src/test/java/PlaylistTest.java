import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class PlaylistTest extends BaseTest {
        @Test(priority = 1, description = "Validate new playlist is created")
        @Parameters({"Playlist"})
        public void createPlaylist (String Playlist) {
            LoginPage loginPage = new LoginPage(driver);
            PlayListPage playListPage = new PlayListPage(driver);
            loginPage.login();

            String notificationText = "Created playlist \"" + Playlist + ".\"";
            playListPage.createNewPlaylist(Playlist);
            playListPage.newPlaylistNotification();
            Assert.assertTrue(playListPage.newPlaylistIsDisplayed());
            Assert.assertTrue(playListPage.newPlaylistNotification().contains(notificationText));
        }
    @Test(priority = 2, description = "Validate song is added to playlist")
    @Parameters({"Playlist"})
    public void addSongToPlaylist(String Playlist) {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.login();

        homePage.searchSong ("Metre");
        homePage.searchSong ("Metre");
        homePage.clickViewAllButton ();
        homePage.clickFirstSong ();
        homePage.clickAddToButton ();
        homePage.clickPlaylist ();
        String notificationText = "Added 1 song into";
        // String notificationText = "Added 1 song into \"" + Playlist + ".\"";
        Assert.assertTrue(homePage.getNotification().contains(notificationText));
    }
    @Test(priority = 3, description = "Validate song is deleted from playlist")
    @Parameters({"Playlist"})
    public void deleteSong(String Playlist) {
        LoginPage loginPage = new LoginPage(driver);
        PlayListPage playListPage = new PlayListPage(driver);
        loginPage.login();
        String msgText1 = "Removed 1 song from \"" + Playlist + ".\"";
        playListPage.selectPlaylist (Playlist);
        playListPage.deleteSongFromPlaylist();
        System.out.println("Message should be: " + msgText1);
        String msgReturn = playListPage.delSongNotificationMsg();
        Assert.assertTrue(msgReturn.contains(msgText1));
    }

        @Test(priority = 4, description = "Validate playlist is renamed using doubleClick")
        @Parameters({"Playlist", "NewPlaylistName"})
        public void renamePlaylist(String Playlist, String NewPlaylistName)  {
            LoginPage loginPage = new LoginPage(driver);
            PlayListPage playListPage = new PlayListPage(driver);
            loginPage.login();
            playListPage.doubleClickPlaylist(Playlist);
            playListPage.enterNewPlaylistName(NewPlaylistName);
               String messageText = "Updated playlist \"" + NewPlaylistName +".\"";
               Assert.assertEquals(playListPage.playlistNameChangeNotify(), messageText);
         //   Assert.assertTrue(playListPage.doesPlaylistExist(NewPlaylistName));
        }
        @Test(priority = 6, description = "Validate playlist is renamed using contextClick")
        @Parameters({"Playlist", "NewPlaylistName"})
        public void renamePlaylistContextClick (String Playlist, String NewPlaylistName) throws Exception {
            LoginPage loginPage = new LoginPage(driver);
            PlayListPage playListPage = new PlayListPage(driver);
            loginPage.login();
            playListPage.createNewPlaylist(Playlist);
            playListPage.contextClickPlaylist(Playlist);
            playListPage.clickEditMenu();
            playListPage.enterNewPlaylistName(NewPlaylistName);
            Assert.assertTrue(playListPage.doesPlaylistExist(NewPlaylistName));
            //  String messageText = "Updated playlist \"" + NewPlaylistName +".\"";
            //   Assert.assertEquals(playlistNameChangeNotify(), messageText);
        }
    @Test(priority = 5, description = "Validate empty renamed playlist is deleted")
    @Parameters({"NewPlaylistName"})
    public void deleteRenamedPlaylist(String NewPlaylistName) {
        LoginPage loginPage = new LoginPage(driver);
        PlayListPage playListPage = new PlayListPage(driver);
        loginPage.login();

        playListPage.selectDeleteRenamedPlaylist(NewPlaylistName);
        String notifyText = "Deleted playlist \"" + NewPlaylistName + ".\"";
        System.out.println("Message should be: " + notifyText);
        Assert.assertTrue(playListPage.deletedPlaylistNotify().contains(notifyText));
    }
}

