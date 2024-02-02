package Processor;

import Exceptions.MButtonNotFoundException;
import Exceptions.MImageCreationErrorException;
import Exceptions.MImageNotFound;
import Exceptions.MTextCreationErrorException;
import Exceptions.MTextNotFoundException;
import Exceptions.SaveFolderNotFoundException;
import MGUI.MButton;
import MGUI.MGUI;
import MGraphics.MAniImage;
import MGraphics.MGraphics;
import MGraphics.MImage;
import MGraphics.MText;
import MLife.MEnviro;
import MLife.MLife;
import MLife.MObject;
import MLife.MPlayer;
import static Processor.Screen.ThisInstance;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kamustaatt1.Main;
import static kamustaatt1.Main.Print;

public abstract class MBrain {
    private static boolean MBrainIsDead = false;
    
    private static Object lock = new Object();
    
    private static File saves[];
    
    private static MAniImage TheLoadingCreechur;
    private static boolean loadingcreech = false;
    private static ArrayList<MImage> LoadingScreenBars = new ArrayList<>();
    
    private static ArrayList<MGraphics> TitleScreenGraphics = new ArrayList<>();
    private static ArrayList<MGUI> TitleScreenGUI = new ArrayList<>();
    
    private static ArrayList<MGraphics> MainMenuGraphics = new ArrayList<>();
    private static ArrayList<MGUI> MainMenuGUI = new ArrayList<>();
    
    private static ArrayList<MGraphics> SaveScreenGraphics = new ArrayList<>();
    private static ArrayList<MGUI> SaveScreenGUI = new ArrayList<>();
    
    private static int CurrChap = -1;
    private static ArrayList<MGraphics> CurrChapGraphics = new ArrayList<>();
    private static ArrayList<MGUI> CurrChapGUI = new ArrayList<>();
    
    //this function gets the game started. loads files and graphics.
    //it is also a boolean because if the boot up fails the returned false value tells Main how to handle the boot fail.
    public static void BootGame() throws SaveFolderNotFoundException, MImageCreationErrorException, MImageNotFound, MTextCreationErrorException, MTextNotFoundException, MButtonNotFoundException {
        if (MBrainIsDead == false) {
                // First, the loading screen. right now, the Window created is pitch-black.
                // Let's add an image of our group logo/symbol along with a progress bar.
                // This progress bar will update with our loading progress.
                try {
                    //The image! Create an image using MGraphics's MImage.CreateMImage() function.
                    MImage.CreateMImage("group_logo", "game_files/resources/loading_screen/grouplogo.png", 200, 200, 650, 200, 0);
                }
                catch (MImageCreationErrorException e) {
                    throw new MImageCreationErrorException("MBrain.BootGame() - creating loading logo");
                }
                
                try {
                    // Make it visible.
                    MImage.FindFromName("group_logo").ToggleVisibility(true);
                }
                catch (MImageNotFound e) {
                    throw new MImageNotFound("MBrain.BootGame() tried to find \""+"group_logo"+"\"");
                }
                    // Kick the Screen to make it keep refreshing, or else we wont see our image!
                    Screen.Refresh();
                    
                
                try {
                    // The progress bar! Create progress bar images using the same MImage function.
                    
                    MImage.CreateMImage("loadingscreenbar 0%", "game_files/resources/loading_screen/loadingbar0.png", 25, 500, 500, 425, 0);
                    MImage.CreateMImage("loadingscreenbar 10%", "game_files/resources/loading_screen/loadingbar10.png", 25, 500, 500, 425, 0);
                    MImage.CreateMImage("loadingscreenbar 20%", "game_files/resources/loading_screen/loadingbar20.png", 25, 500, 500, 425, 0);
                    MImage.CreateMImage("loadingscreenbar 30%", "game_files/resources/loading_screen/loadingbar30.png", 25,500,500, 425, 0);
                    MImage.CreateMImage("loadingscreenbar 40%", "game_files/resources/loading_screen/loadingbar40.png", 25, 500, 500, 425, 0);
                    MImage.CreateMImage("loadingscreenbar 50%", "game_files/resources/loading_screen/loadingbar50.png", 25, 500, 500, 425, 0);
                    MImage.CreateMImage("loadingscreenbar 60%", "game_files/resources/loading_screen/loadingbar60.png", 25, 500, 500, 425, 0);
                    MImage.CreateMImage("loadingscreenbar 70%", "game_files/resources/loading_screen/loadingbar70.png", 25, 500, 500, 425, 0);
                    MImage.CreateMImage("loadingscreenbar 80%", "game_files/resources/loading_screen/loadingbar80.png", 25, 500, 500, 425, 0);
                    MImage.CreateMImage("loadingscreenbar 90%", "game_files/resources/loading_screen/loadingbar90.png", 25, 500, 500, 425, 0);
                    MImage.CreateMImage("loadingscreenbar 100%", "game_files/resources/loading_screen/loadingbar100.png", 25, 500, 500, 425, 0);
                }
                catch (MImageCreationErrorException e) {
                    throw new MImageCreationErrorException("MBrain.BootGame() - creating loading screen bars");
                }
                
                try {
                    LoadingScreenBars.add(MImage.FindFromName("loadingscreenbar 0%"));
                    LoadingScreenBars.add(MImage.FindFromName("loadingscreenbar 10%"));
                    LoadingScreenBars.add(MImage.FindFromName("loadingscreenbar 20%"));
                    LoadingScreenBars.add(MImage.FindFromName("loadingscreenbar 30%"));
                    LoadingScreenBars.add(MImage.FindFromName("loadingscreenbar 40%"));
                    LoadingScreenBars.add(MImage.FindFromName("loadingscreenbar 50%"));
                    LoadingScreenBars.add(MImage.FindFromName("loadingscreenbar 60%"));
                    LoadingScreenBars.add(MImage.FindFromName("loadingscreenbar 70%"));
                    LoadingScreenBars.add(MImage.FindFromName("loadingscreenbar 80%"));
                    LoadingScreenBars.add(MImage.FindFromName("loadingscreenbar 90%"));
                    LoadingScreenBars.add(MImage.FindFromName("loadingscreenbar 100%"));
                }
                catch (MImageNotFound e) {
                    throw new MImageNotFound("MBrain.BootGame() tried to find loading screen bars");
                }
                
                try {
                    ArrayList<String> loading_creechur = new ArrayList<>();
                    loading_creechur.add("game_files/resources/loading_screen/loadingcreechur/0.png");
                    loading_creechur.add("game_files/resources/loading_screen/loadingcreechur/1.png");
                    loading_creechur.add("game_files/resources/loading_screen/loadingcreechur/2.png");
                    loading_creechur.add("game_files/resources/loading_screen/loadingcreechur/3.png");
                    MAniImage.CreateMAniImage("loading_creechur", loading_creechur, 0, 200, 200, 650, 200, 0);
                    TheLoadingCreechur = (MAniImage.FindFromName("loading_creechur"));
                }
                catch (MImageCreationErrorException e) {
                    throw new MImageCreationErrorException("MBrain.BootGame() - creating loading creechur");
                }
                
                ToggleLoadingBars(true, 0);

                // Second, let's store the save files, if there are any.
                try {
                    // scan the saves folder for files and store them in an array
                    File SavesFolder = new File("game_files/saves");
                    if (SavesFolder.exists() && SavesFolder.isDirectory()) {
                        saves = SavesFolder.listFiles();
                    }
                    else {
                        throw new SaveFolderNotFoundException("MBrain.BootGame()");
                    }

                }
                catch (SaveFolderNotFoundException e) {
                    throw new SaveFolderNotFoundException("MBrain.BootGame()");
                }
                
                //move the bar to 10%
                ToggleLoadingBars(true, 1);
                
                //Third, let's preload the Title Screen
                    //titlescreen image
                    try {
                        MImage.CreateMImage("titlescreen_image", "game_files/resources/title_screen/titlescreenimage.png", 750, 1500, 0, 0, 0);
                        TitleScreenGraphics.add(MImage.FindFromName("titlescreen_image"));
                    }
                    catch (MImageCreationErrorException e) {
                        throw new MImageCreationErrorException("MBrain.BootGame() - creating titlescreen image");
                    }
                    catch (MImageNotFound ee) {
                        throw new MImageNotFound("MBrain.BootGame() tried to find titlescreen_image");
                    }
                    
                    //titlescreen title text
                    try {
                        MText.CreateMText("TitleScreenTitleText","Kamusta ka na?", "onciale", 150, 20, 25, 155, 175, "BLACK", 1);
                        TitleScreenGraphics.add(MText.FindFromName("TitleScreenTitleText"));
                    }
                    catch (MTextCreationErrorException eee) {
                        throw new MTextCreationErrorException("MBrain.BootGame() - creating titlescreen title text");
                    }
                    catch (MTextNotFoundException eeee) {
                        throw new MTextNotFoundException("MBrain.BootGame() tried to find TitleScreenTitleText");
                    }
                    
                    //titlescreen press anywhere to start text
                    try {
                        MText.CreateMText("TitleScreenInstructText","CLICK ANYWHERE ON THE SCREEN TO START", "onciale", 15, 150, 25, 550, 600, "BLACK", 1);
                        TitleScreenGraphics.add(MText.FindFromName("TitleScreenInstructText"));
                    }
                    catch (MTextCreationErrorException eee) {
                        throw new MTextCreationErrorException("MBrain.BootGame() - creating titlescreen title text");
                    }
                    catch (MTextNotFoundException eeee) {
                        throw new MTextNotFoundException("MBrain.BootGame() tried to find TitleScreenTitleText");
                    }
                    
                    //titlescreen minor texts
                    //
                    
                    //titlescreen hitbox
                    try {
                        MButton.CreateButtonHitbox("titlescreenhitbox", "titlescreenenter", 0, 0, 1500, 750);
                        TitleScreenGUI.add(MButton.FindFromName("titlescreenhitbox"));
                    }
                    catch (MButtonNotFoundException e) {
                        throw new MButtonNotFoundException("MButton.BootGame() tried to find titlescreenhitbox");
                    }
                    
                    
                //move the bar to 20%
                ToggleLoadingBars(true, 2);
                
                //Fourth, preload the main menu
                    //MAIN MENU BACKGROUND
                    try {
                        MImage.CreateMImage("MainMenuBackground", "game_files/resources/main_menu/mainmenubackground.png", 750, 1500, 0, 0, 0);
                        MainMenuGraphics.add(MImage.FindFromName("MainMenuBackground"));
                    }
                    catch (MImageCreationErrorException e) {
                        throw new MImageCreationErrorException("MBrain.BootGame() - creating MainMenuBackground image");
                    }
                    catch (MImageNotFound ee) {
                        throw new MImageNotFound("MBrain.BootGame() tried to find MainMenuBackground");
                    }
                    
                    //START GAME BUTTON
                    try {
                        MImage.CreateMImage("StartGameButton", "game_files/resources/main_menu/button.png", 100, 500, 500, 100, 1);
                        MainMenuGraphics.add(MImage.FindFromName("StartGameButton"));
                        MButton.CreateButtonHitbox("StartGameButtonHitbox", "startnewgame", 500, 100, 1000,200);
                        MainMenuGUI.add(MButton.FindFromName("StartGameButtonHitbox"));
                        MText.CreateMText("NewGameButtonText","NEW GAME", "onciale", 50, 150, 25, 575, 170, "BLACK", 2);
                        MainMenuGraphics.add(MText.FindFromName("NewGameButtonText"));
                    }
                    catch (MImageCreationErrorException e) {
                        throw new MImageCreationErrorException("MBrain.BootGame() - creating StartGameButton image");
                    }
                    catch (MImageNotFound ee) {
                        throw new MImageNotFound("MBrain.BootGame() tried to find StartGameButton");
                    }
                    catch (MButtonNotFoundException e) {
                        throw new MButtonNotFoundException("MButton.BootGame() tried to find StartGameButtonHitbox");
                    }
                    catch (MTextCreationErrorException eee) {
                        throw new MTextCreationErrorException("MBrain.BootGame() - creating StartGameButtonText text");
                    }
                    catch (MTextNotFoundException eeee) {
                        throw new MTextNotFoundException("MBrain.BootGame() tried to find StartGameButtonText");
                    }
                    
                    //LOAD SAVE BUTTON
                    try {
                        MImage.CreateMImage("LoadGameButton", "game_files/resources/main_menu/button.png", 100, 500, 500, 250, 1);
                        MainMenuGraphics.add(MImage.FindFromName("LoadGameButton"));
                        MButton.CreateButtonHitbox("LoadGameButtonHitbox", "loadoldgame", 500, 250, 1000, 350);
                        MainMenuGUI.add(MButton.FindFromName("LoadGameButtonHitbox"));
                        MText.CreateMText("LoadGameButtonText","LOAD GAME", "onciale", 50, 150, 25, 575, 320, "BLACK", 2);
                        MainMenuGraphics.add(MText.FindFromName("LoadGameButtonText"));
                    }
                    catch (MImageCreationErrorException e) {
                        throw new MImageCreationErrorException("MBrain.BootGame() - creating LoadGameButton image");
                    }
                    catch (MImageNotFound ee) {
                        throw new MImageNotFound("MBrain.BootGame() tried to find LoadGameButton");
                    }
                    catch (MButtonNotFoundException e) {
                        throw new MButtonNotFoundException("MButton.BootGame() tried to find LoadGameButtonHitbox");
                    }
                    catch (MTextCreationErrorException eee) {
                        throw new MTextCreationErrorException("MBrain.BootGame() - creating LoadGameButtonText text");
                    }
                    catch (MTextNotFoundException eeee) {
                        throw new MTextNotFoundException("MBrain.BootGame() tried to find LoadGameButtonText");
                    }
                    
                    //SETTINGS BUTTON
                    try {
                        MImage.CreateMImage("MMSettingsButton", "game_files/resources/main_menu/button.png", 100, 500, 500, 400, 1);
                        MainMenuGraphics.add(MImage.FindFromName("MMSettingsButton"));
                        MButton.CreateButtonHitbox("MMSettingsButtonHitbox", "openMMSettings", 500, 400, 1000, 500);
                        MainMenuGUI.add(MButton.FindFromName("MMSettingsButtonHitbox"));
                        MText.CreateMText("MMSettingsButtonText","SETTINGS", "onciale", 50, 150, 25, 575, 470, "BLACK", 2);
                        MainMenuGraphics.add(MText.FindFromName("MMSettingsButtonText"));
                    }
                    catch (MImageCreationErrorException e) {
                        throw new MImageCreationErrorException("MBrain.BootGame() - creating MMSettingsButton image");
                    }
                    catch (MImageNotFound ee) {
                        throw new MImageNotFound("MBrain.BootGame() tried to find MMSettingsButton");
                    }
                    catch (MButtonNotFoundException e) {
                        throw new MButtonNotFoundException("MButton.BootGame() tried to find MMSettingsButtonHitbox");
                    }
                    catch (MTextCreationErrorException eee) {
                        throw new MTextCreationErrorException("MBrain.BootGame() - creating LoadGameButtonText text");
                    }
                    catch (MTextNotFoundException eeee) {
                        throw new MTextNotFoundException("MBrain.BootGame() tried to find LoadGameButtonText");
                    }
                    
                    //CREDITS BUTTON
                    try {
                        MImage.CreateMImage("CreditsButton", "game_files/resources/main_menu/button.png", 100, 500, 500, 550, 1);
                        MainMenuGraphics.add(MImage.FindFromName("CreditsButton"));
                        MButton.CreateButtonHitbox("CreditsButtonHitbox", "openMMCredits", 500, 550, 1000, 650);
                        MainMenuGUI.add(MButton.FindFromName("CreditsButtonHitbox"));
                        MText.CreateMText("CreditsButton","CREDITS", "onciale", 50, 150, 25, 575, 620, "BLACK", 2);
                        MainMenuGraphics.add(MText.FindFromName("CreditsButton"));
                    }
                    catch (MImageCreationErrorException e) {
                        throw new MImageCreationErrorException("MBrain.BootGame() - creating CreditsButton image");
                    }
                    catch (MImageNotFound ee) {
                        throw new MImageNotFound("MBrain.BootGame() tried to find CreditsButton");
                    }
                    catch (MButtonNotFoundException e) {
                        throw new MButtonNotFoundException("MButton.BootGame() tried to find CreditsButtonHitbox");
                    }
                    catch (MTextCreationErrorException eee) {
                        throw new MTextCreationErrorException("MBrain.BootGame() - creating LoadGameButtonText text");
                    }
                    catch (MTextNotFoundException eeee) {
                        throw new MTextNotFoundException("MBrain.BootGame() tried to find LoadGameButtonText");
                    }
                
                //move the bar to 30%
                ToggleLoadingBars(true, 3);
                
                //Fifth, decode save files
                if (saves.length > 0) {
                    //decode
                }
                
                //move the bar to 40%
                ToggleLoadingBars(true, 4);
                
                //Sixth, scrape the preview data of the save files
                if (saves.length > 0) {
                    //scrape
                }
                
                //move the bar to 50%
                ToggleLoadingBars(true, 5);
                
                //Seventh, preload the save screen
                    //SAVE SCREEN BACKGROUND
                    try {
                        MImage.CreateMImage("SaveScreenBackground", "game_files/resources/save_screen/savescreenbackground.png", 750, 1500, 0, 0, 0);
                        SaveScreenGraphics.add(MImage.FindFromName("SaveScreenBackground"));
                    }
                    catch (MImageCreationErrorException e) {
                        throw new MImageCreationErrorException("MBrain.BootGame() - creating SaveScreenBackground image");
                    }
                    catch (MImageNotFound ee) {
                        throw new MImageNotFound("MBrain.BootGame() tried to find SaveScreenBackground");
                    }
                    
                    if (saves.length == 0) {
                        try {
                            MImage.CreateMImage("SaveScreenNoSavesNotice", "game_files/resources/save_screen/savefilebox.png", 100, 500, 500, 250, 1);
                            SaveScreenGraphics.add(MImage.FindFromName("SaveScreenNoSavesNotice"));
                            MText.CreateMText("SaveScreenNoSavesNoticeText","You don't have any saves.", "onciale", 20, 150, 100, 600, 300, "BLACK", 2);
                            SaveScreenGraphics.add(MText.FindFromName("SaveScreenNoSavesNoticeText"));
                        }
                        catch (MImageCreationErrorException e) {
                            throw new MImageCreationErrorException("MBrain.BootGame() - creating SaveScreenNoSavesNotice image");
                        }
                        catch (MImageNotFound ee) {
                            throw new MImageNotFound("MBrain.BootGame() tried to find SaveScreenNoSavesNotice");
                        }
                        
                        //back button
                        try {
                            MImage.CreateMImage("BackToMM", "game_files/resources/save_screen/savefilebox.png", 50, 100, 900, 350, 1);
                            SaveScreenGraphics.add(MImage.FindFromName("BackToMM"));
                            MButton.CreateButtonHitbox("BackToMMHitbox", "backtomm", 900, 350, 1000, 400);
                            SaveScreenGUI.add(MButton.FindFromName("BackToMMHitbox"));
                            MText.CreateMText("BackToMMText","BACK", "onciale", 20, 150, 100, 910, 385, "BLACK", 2);
                            SaveScreenGraphics.add(MText.FindFromName("BackToMMText"));
                        }
                        catch (MImageCreationErrorException e) {
                            throw new MImageCreationErrorException("MBrain.BootGame() - creating BackToMM image");
                        }
                        catch (MImageNotFound ee) {
                            throw new MImageNotFound("MBrain.BootGame() tried to find BackToMM");
                        }
                        catch (MButtonNotFoundException e) {
                            throw new MButtonNotFoundException("MButton.BootGame() tried to find SaveFileBox1Hitbox");
                        }
                        catch (MTextCreationErrorException eee) {
                            throw new MTextCreationErrorException("MBrain.BootGame() - creating SaveFileBox1TextTitle text");
                        }
                        catch (MTextNotFoundException eeee) {
                            throw new MTextNotFoundException("MBrain.BootGame() tried to find SaveFileBox1TextTitle");
                        }
                    }
                    else if (saves.length > 0) {
                        //SLOT 1
                        try {
                            MImage.CreateMImage("SaveFileBox1", "game_files/resources/save_screen/savefilebox.png", 100, 500, 500, 100, 1);
                            SaveScreenGraphics.add(MImage.FindFromName("SaveFileBox1"));
                            MButton.CreateButtonHitbox("SaveFileBox1Hitbox", "loadsave1", 500, 100, 1000, 200);
                            SaveScreenGUI.add(MButton.FindFromName("SaveFileBox1Hitbox"));
                            MText.CreateMText("SaveFileBox1TextTitle","SaveTitle", "onciale", 30, 150, 100, 505, 140, "BLACK", 2);
                            SaveScreenGraphics.add(MText.FindFromName("SaveFileBox1TextTitle"));
                        }
                        catch (MImageCreationErrorException e) {
                            throw new MImageCreationErrorException("MBrain.BootGame() - creating SaveFileBox1 image");
                        }
                        catch (MImageNotFound ee) {
                            throw new MImageNotFound("MBrain.BootGame() tried to find SaveFileBox1");
                        }
                        catch (MButtonNotFoundException e) {
                            throw new MButtonNotFoundException("MButton.BootGame() tried to find SaveFileBox1Hitbox");
                        }
                        catch (MTextCreationErrorException eee) {
                            throw new MTextCreationErrorException("MBrain.BootGame() - creating SaveFileBox1TextTitle text");
                        }
                        catch (MTextNotFoundException eeee) {
                            throw new MTextNotFoundException("MBrain.BootGame() tried to find SaveFileBox1TextTitle");
                        }
                        
                        //at least 2 saves
                        if (saves.length > 1) {
                            //SLOT 2
                            try {
                                MImage.CreateMImage("SaveFileBox2", "game_files/resources/save_screen/savefilebox.png", 100, 500, 500, 250, 1);
                                SaveScreenGraphics.add(MImage.FindFromName("SaveFileBox2"));
                                MButton.CreateButtonHitbox("SaveFileBox2Hitbox", "loadsave2", 500, 250, 1000, 350);
                                SaveScreenGUI.add(MButton.FindFromName("SaveFileBox2Hitbox"));
                                MText.CreateMText("SaveFileBox2TextTitle","SaveTitle", "onciale", 30, 150, 100, 505, 290, "BLACK", 2);
                                SaveScreenGraphics.add(MText.FindFromName("SaveFileBox2TextTitle"));
                            }
                            catch (MImageCreationErrorException e) {
                                throw new MImageCreationErrorException("MBrain.BootGame() - creating SaveFileBox2 image");
                            }
                            catch (MImageNotFound ee) {
                                throw new MImageNotFound("MBrain.BootGame() tried to find SaveFileBox2");
                            }
                            catch (MButtonNotFoundException e) {
                                throw new MButtonNotFoundException("MButton.BootGame() tried to find SaveFileBox2Hitbox");
                            }
                            catch (MTextCreationErrorException eee) {
                                throw new MTextCreationErrorException("MBrain.BootGame() - creating SaveFileBox2TextTitle text");
                            }
                            catch (MTextNotFoundException eeee) {
                                throw new MTextNotFoundException("MBrain.BootGame() tried to find SaveFileBox2TextTitle");
                            }
                        }
                        //at least 3 saves
                        if (saves.length > 2) {
                            //SLOT 3
                            try {
                                MImage.CreateMImage("SaveFileBox3", "game_files/resources/save_screen/savefilebox.png", 100, 500, 500, 400, 1);
                                SaveScreenGraphics.add(MImage.FindFromName("SaveFileBox3"));
                                MButton.CreateButtonHitbox("SaveFileBox3Hitbox", "loadsave3", 500, 400, 1000, 500);
                                SaveScreenGUI.add(MButton.FindFromName("SaveFileBox3Hitbox"));
                                MText.CreateMText("SaveFileBox3TextTitle","SaveTitle", "onciale", 30, 150, 100, 505, 440, "BLACK", 2);
                                SaveScreenGraphics.add(MText.FindFromName("SaveFileBox3TextTitle"));
                            }
                            catch (MImageCreationErrorException e) {
                                throw new MImageCreationErrorException("MBrain.BootGame() - creating SaveFileBox3 image");
                            }
                            catch (MImageNotFound ee) {
                                throw new MImageNotFound("MBrain.BootGame() tried to find SaveFileBox3");
                            }
                            catch (MButtonNotFoundException e) {
                                throw new MButtonNotFoundException("MButton.BootGame() tried to find SaveFileBox3Hitbox");
                            }
                            catch (MTextCreationErrorException eee) {
                                throw new MTextCreationErrorException("MBrain.BootGame() - creating SaveFileBox3TextTitle text");
                            }
                            catch (MTextNotFoundException eeee) {
                                throw new MTextNotFoundException("MBrain.BootGame() tried to find SaveFileBox3TextTitle");
                            }
                        }
                        //at least 4 saves
                        if (saves.length > 3) {
                            //SLOT 4
                            try {
                                MImage.CreateMImage("SaveFileBox4", "game_files/resources/save_screen/savefilebox.png", 100, 500, 500, 550, 1);
                                SaveScreenGraphics.add(MImage.FindFromName("SaveFileBox4"));
                                MButton.CreateButtonHitbox("SaveFileBox4Hitbox", "loadsave4", 500, 550, 1000, 650);
                                SaveScreenGUI.add(MButton.FindFromName("SaveFileBox4Hitbox"));
                                MText.CreateMText("SaveFileBox4TextTitle","SaveTitle", "onciale", 30, 150, 100, 505, 590, "BLACK", 2);
                                SaveScreenGraphics.add(MText.FindFromName("SaveFileBox4TextTitle"));
                            }
                            catch (MImageCreationErrorException e) {
                                throw new MImageCreationErrorException("MBrain.BootGame() - creating SaveFileBox3 image");
                            }
                            catch (MImageNotFound ee) {
                                throw new MImageNotFound("MBrain.BootGame() tried to find SaveFileBox3");
                            }
                            catch (MButtonNotFoundException e) {
                                throw new MButtonNotFoundException("MButton.BootGame() tried to find SaveFileBox3Hitbox");
                            }
                            catch (MTextCreationErrorException eee) {
                                throw new MTextCreationErrorException("MBrain.BootGame() - creating SaveFileBox3TextTitle text");
                            }
                            catch (MTextNotFoundException eeee) {
                                throw new MTextNotFoundException("MBrain.BootGame() tried to find SaveFileBox3TextTitle");
                            }
                        }
                    }

                //move the bar to 60%
                ToggleLoadingBars(true, 6);
                
                //Eighth, preload the settings screen
                
                //move the bar to 70%
                ToggleLoadingBars(true, 7);
                
                //Ninth, preload the credits screen

                //move the bar to 80%
                ToggleLoadingBars(true, 8);
                
                //Ten, scrape preview
                
                //move the bar to 90%
                ToggleLoadingBars(true, 9);
                
                //Eleven, look for the error log folder
                
                //move the bar to 100%
                ToggleLoadingBars(true, 10);
                
                //Twelve, create an empty error log for the current date
                
                //We're done loading, for now. Time to start the game with the Title Screen.
                try {
                    ToggleLoadingBars(false, -1);
                    MImage.FindFromName("group_logo").ToggleVisibility(false);
                }
                catch (MImageNotFound e) {
                    throw new MImageNotFound("MBrain.BootGame() tried to find loading screen bar 0%");
                }
                ToggleTitleScreen(true);
        }
    }
    
    public static void loadsave(int slot) {
        System.out.println("uwu");
    }
    
    public static void startgame() throws MImageNotFound {
        CurrChap = 0;
        CreatePlayer();
        LoadWorld();
        Preload(CurrChap);
    }
    
    public static void NextChap(int nextscene) {
        for (int a=0; a<CurrChapGraphics.size(); a++) {
            CurrChapGraphics.get(a).DeleteSelf();
        }
        
        CurrChapGraphics.clear();
        
        
        try {
            Preload(nextscene);
        } catch (MImageNotFound ex) {
            Main.ForceStopThrown("The MImage could not be found! This error was reported at: "+"MBrain.NextChap() for "+nextscene);
        }
    }
    
    public static void ToggleLoadingBars(boolean tof, int percen) throws MImageNotFound {
        if (tof) {
            ToggleLoadingBars(false, -1);
            LoadingScreenBars.get(percen).ToggleVisibility(true);
        }
        else {
            MImage.FindFromName("loadingscreenbar 0%").ToggleVisibility(false);
            MImage.FindFromName("loadingscreenbar 10%").ToggleVisibility(false);
            MImage.FindFromName("loadingscreenbar 20%").ToggleVisibility(false);
            MImage.FindFromName("loadingscreenbar 30%").ToggleVisibility(false);
            MImage.FindFromName("loadingscreenbar 40%").ToggleVisibility(false);
            MImage.FindFromName("loadingscreenbar 50%").ToggleVisibility(false);
            MImage.FindFromName("loadingscreenbar 60%").ToggleVisibility(false);
            MImage.FindFromName("loadingscreenbar 70%").ToggleVisibility(false);
            MImage.FindFromName("loadingscreenbar 80%").ToggleVisibility(false);
            MImage.FindFromName("loadingscreenbar 90%").ToggleVisibility(false);
            MImage.FindFromName("loadingscreenbar 100%").ToggleVisibility(false);
        }
    }
    
    public static void ToggleTitleScreen(boolean tof) {
        for (int i=0; i<TitleScreenGraphics.size(); i++) {
            TitleScreenGraphics.get(i).ToggleVisibility(tof);
        }
        for (int j=0; j<TitleScreenGUI.size(); j++) {
            TitleScreenGUI.get(j).ToggleEnabled(tof);
        }
    }
    
    public static void ToggleMainMenu(boolean tof) {
        for (int i=0; i<MainMenuGraphics.size(); i++) {
            MainMenuGraphics.get(i).ToggleVisibility(tof);
        }
        for (int j=0; j<MainMenuGUI.size(); j++) {
            MainMenuGUI.get(j).ToggleEnabled(tof);
        }
    }
    
    public static void ToggleSaveScreen(boolean tof) {
        for (int i=0; i<SaveScreenGraphics.size(); i++) {
            SaveScreenGraphics.get(i).ToggleVisibility(tof);
        }
        for (int j=0; j<SaveScreenGUI.size(); j++) {
            SaveScreenGUI.get(j).ToggleEnabled(tof);
        }
    }
    
    public static void CreatePlayer() {
        try {
            MPlayer.CreatePlayer("uwu");
            ArrayList<String> protagspritesfilenames = new ArrayList<>();
            protagspritesfilenames.add("game_files/resources/character_sprites/test_protag/protag_idle_R.png");
            protagspritesfilenames.add("game_files/resources/character_sprites/test_protag/protag_idle_L.png");
            protagspritesfilenames.add("game_files/resources/character_sprites/test_protag/protag_walk_R/0.png");
            protagspritesfilenames.add("game_files/resources/character_sprites/test_protag/protag_walk_R/1.png");
            protagspritesfilenames.add("game_files/resources/character_sprites/test_protag/protag_walk_R/2.png");
            protagspritesfilenames.add("game_files/resources/character_sprites/test_protag/protag_walk_R/3.png");
            protagspritesfilenames.add("game_files/resources/character_sprites/test_protag/protag_walk_L/0.png");
            protagspritesfilenames.add("game_files/resources/character_sprites/test_protag/protag_walk_L/1.png");
            protagspritesfilenames.add("game_files/resources/character_sprites/test_protag/protag_walk_L/2.png");
            protagspritesfilenames.add("game_files/resources/character_sprites/test_protag/protag_walk_L/3.png");
            MAniImage.CreateMAniImage("Test_Protag_Graphics", protagspritesfilenames, 0, 250, 250, 0, 0, 1);
            LinkMLifeToMGraphics(MLife.GetPlayer(), MAniImage.FindFromName("Test_Protag_Graphics"), new Vector2(0,0));
            MLife.GetPlayer().ToggleVisibility(true);
        } catch (MImageCreationErrorException ex) {
            Logger.getLogger(MBrain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MImageNotFound ex) {
            Logger.getLogger(MBrain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //DO NOT call before character is created.
    public static void LoadWorld() {
        try {
            
            
            //Load SchoolHallwayA
                MEnviro.CreateMEnviro("SchoolHallwayA", 0, 0);
                MImage.CreateMImage("SchoolHallwayA_BG", "game_files/resources/locations/SchoolHallwayA/BG.png", 750, 1500, 0, 0, 0);
                LinkMLifeToMGraphics(MEnviro.MEnviroFindFromName("SchoolHallwayA"), MImage.FindFromName("SchoolHallwayA_BG"), new Vector2(0,0));

                MObject.CreateMObject("box", 750, 600);
                MImage.CreateMImage("box_grph", "game_files/resources/locations/SchoolHallwayA/box.png", 100, 100, 0, 0, 1);
                LinkMLifeToMGraphics(MObject.MObjectFindFromName("box"), MImage.FindFromName("box_grph"), new Vector2(0,0));
                
                MEnviro.MEnviroFindFromName("SchoolHallwayA").AddItemToContainer("box");
                MEnviro.MEnviroFindFromName("SchoolHallwayA").ToggleVisibility(true);
                
        } catch (MImageCreationErrorException | MImageNotFound ex) {
            Logger.getLogger(MBrain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void Preload(int Chapter) throws MImageNotFound {
        loadingcreech = true;
        animateloadingcreature();
        TheLoadingCreechur.ToggleVisibility(true);
        ToggleLoadingBars(true, 0);
        
        switch (Chapter) {
            case 0:
                ;
                break;
        }
    }
    
    public static void animateloadingcreature() {
        new Thread (new Runnable() {
            public void run() {
                int currframe = 0;
                while(loadingcreech) {
                    synchronized (lock) {
                        try {
                            lock.wait(250);
                        } catch (InterruptedException ex) {
                            ;
                        }
                        
                        TheLoadingCreechur.SetFrameIndex(currframe);
                        if (currframe == TheLoadingCreechur.GetMaxFrame()) {
                            currframe = 0;
                        }
                        else {
                            currframe++;
                        }
                    }
                }
            }
        }).start();
    }
    
    public static void LinkMLifeToMGraphics(MLife ml, MGraphics mg, Vector2 offset) {
        Object locket = new Object();
        new Thread (() -> {
            int type = -1; //0 = MImage, 1 = MText, 2 = MAniImage
            MImage Mi = null;
            MText Mt = null;
            MAniImage Mai = null;
            try {
                switch (mg.getClass().getSimpleName()) {
                    case "MImage":
                        Mi = MImage.FindFromHash(mg.getID_hash());
                        type = 0;
                        break;
                        
                    case "MText":
                        Mt = MText.FindFromHash(mg.getID_hash());
                        type = 1;
                        break;
                        
                    case "MAniImage":
                        Mai = MAniImage.FindFromHash(mg.getID_hash());
                        type = 2;
                        break;
                }
                
                switch  (type) {
                    case 0:
                        while(true) {
                            synchronized (locket) {
                                try {
                                    locket.wait(25);
                                    mg.SetPxPy(ml.GetPLocation().pX+offset.pX, ml.GetPLocation().pY+offset.pY);
                                    if (MLife.GetPlayer().GetWLocation() == ml.GetWLocation()) {
                                        mg.ToggleVisibility(true);
                                    }
                                    else {
                                        mg.ToggleVisibility(false);
                                    }
                                } catch (InterruptedException ex) {
                                    System.out.println(ex);
                                }
                                
                                
                            }
                        }
                        
                        
                    case 1:
                        while(true) {
                            synchronized (locket) {
                                try {
                                    locket.wait(25);
                                    mg.SetPxPy(ml.GetPLocation().pX+offset.pX, ml.GetPLocation().pY+offset.pY);
                                    if (MLife.GetPlayer().GetWLocation() == ml.GetWLocation()) {
                                        mg.ToggleVisibility(true);
                                    }
                                    else {
                                        mg.ToggleVisibility(false);
                                    }
                                } catch (InterruptedException ex) {
                                    System.out.println(ex);
                                }
                                
                                
                            }
                        }
                        
                    case 2:
                        while(true) {
                            synchronized (locket) {
                                try {
                                    locket.wait(25);
                                    mg.SetPxPy(ml.GetPLocation().pX+offset.pX, ml.GetPLocation().pY+offset.pY);
                                    Mai.SetFrameIndex(ml.GetCurrFrame());
                                    if ((MLife.GetPlayer().GetWLocation() == ml.GetWLocation())&&ml.GetVisibility()) {
                                        mg.ToggleVisibility(true);
                                    }
                                    else {
                                        mg.ToggleVisibility(false);
                                    }
                                } catch (InterruptedException ex) {
                                    System.out.println(ex);
                                }
                            }
                        }
                        
                    
                }
                
            } catch (Exception ex) {
                Logger.getLogger(MBrain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }
    
    public static void receive(int ID, int value) {
        System.out.println(ID);
        
        switch (ID) {
            //right arrow key
            case -1371463995:
                MLife.GetPlayer().Move(60);
                break;
            
            
            //left arrow key
            case 1720068282:
                MLife.GetPlayer().Move(-60);
                break;
                
            //titlescreenhitbox
            case -341312740:
                ToggleTitleScreen(false);
                ToggleMainMenu(true);
                break;
                
            // StartGameButtonHitbox
            case 506059934:
                ToggleMainMenu(false);
                try {
                    startgame();
                } catch (MImageNotFound ex) {
                    Main.ForceStopThrown("The MImage could not be found! This error was reported at: "+"MBrain.receive case StartGameButtonHitbox");
                }
                break;

                
            // LoadGameButtonHitbox
            case -28559006:
                ToggleMainMenu(false);
                ToggleSaveScreen(true);
                break;
                
            // MMSettingsButtonHitbox
            case 1916363949:
                ToggleMainMenu(false);
                break;
                
            // CreditsButtonHitbox
            case -739377980:
                ToggleMainMenu(false);
                break;
                
            //SaveFileBox1
            case -484824489:
                ToggleSaveScreen(false);
                loadsave(0);
                break;
                
            //SaveFileBox2
            
                
            //SaveFileBox3
                
            //SaveFileBox4
                
            //exit save screen to main menu
            case -357075206:
                ToggleSaveScreen(false);
                ToggleMainMenu(true);
                break;
        }
    }
    
    public static void kill() {
        MBrainIsDead = true;
    }
}
