/*
 Copyright (C) 2022 Petr Panteleyev

 This program is free software: you can redistribute it and/or modify it under the
 terms of the GNU General Public License as published by the Free Software
 Foundation, either version 3 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful, but WITHOUT ANY
 WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with this
 program. If not, see <https://www.gnu.org/licenses/>.
 */
package org.panteleyev.freedesktop.menu;

import java.util.Set;

/**
 * Menu Categories. The list of Main Categories consist of those categories that every conforming desktop environment
 * MUST support. By including one of these categories in an application's desktop entry file the application will be
 * ensured that it will show up in a section of the application menu dedicated to this category.
 */
public enum Category {
    /**
     * A multimedia (audio/video) application.
     */
    AUDIO_VIDEO("AudioVideo"),

    /**
     * An audio application. Desktop entry must include AudioVideo as well.
     */
    AUDIO("Audio"),
    /**
     * A video application.	Desktop entry must include AudioVideo as well.
     */
    VIDEO("Video"),
    /**
     * An application for development.
     */
    DEVELOPMENT("Development"),
    /**
     * Educational software.
     */
    EDUCATION("Education"),
    /**
     * A game
     */
    GAME("Game"),
    /**
     * Graphical application.
     */
    GRAPHICS("Graphics"),
    /**
     * Network application such as a web browser.
     */
    NETWORK("Network"),
    /**
     * An office type application
     */
    OFFICE("Office"),
    /**
     * Scientific software.
     */
    SCIENCE("Science"),
    /**
     * Settings applications. Entries may appear in a separate menu or as part of a &quot;Control Center&quot;.
     */
    SETTINGS("Settings"),
    /**
     * System application, &quot;System Tools&quot; such as say a log viewer or network monitor
     */
    SYSTEM("System"),
    /**
     * Small utility application, &quot;Accessories&quot;.
     */
    UTILITY("Utility"),
    /**
     * A tool to build applications.
     */
    BUILDING("Building", DEVELOPMENT),
    /**
     * A tool to debug applications.
     */
    DEBUGGER("Debugger", DEVELOPMENT),
    /**
     * IDE application.
     */
    IDE("IDE", DEVELOPMENT),
    /**
     * A GUI designer application.
     */
    GUI_DESIGNER("GUIDesigner", DEVELOPMENT),
    /**
     * A profiling tool.
     */
    PROFILING("Profiling", DEVELOPMENT),
    /**
     * Applications like cvs or subversion.
     */
    REVISION_CONTROL("RevisionControl", DEVELOPMENT),
    /**
     * A translation tool.
     */
    TRANSLATION("Translation", DEVELOPMENT),
    /**
     * Calendar application.
     */
    CALENDAR("Calendar", OFFICE),
    /**
     * E.g. an address book.
     */
    CONTACT_MANAGEMENT("ContactManagement", OFFICE),
    /**
     * Application to manage a database.
     */
    DATABASE("Database", OFFICE, DEVELOPMENT, AUDIO_VIDEO),
    /**
     * Chart application.
     */
    CHART("Chart", OFFICE),
    /**
     * Email application.
     */
    EMAIL("Email", OFFICE, NETWORK),
    /**
     * Application to manage your finance.
     */
    FINANCE("Finance", OFFICE),
    /**
     * A flowchart application.
     */
    FLOW_CHART("FlowChart", OFFICE),
    /**
     * Tool to manage your PDA.
     */
    PDA("PDA", OFFICE),
    /**
     * Project management application.
     */
    PROJECT_MANAGEMENT("ProjectManagement", OFFICE, DEVELOPMENT),
    /**
     * Presentation software.
     */
    PRESENTATION("Presentation", OFFICE),
    /**
     * A spreadsheet.
     */
    SPREADSHEET("Spreadsheet", OFFICE),
    /**
     * A word processor.
     */
    WORD_PROCESSOR("WordProcessor", OFFICE),
    /**
     * 2D based graphical application.
     */
    X2D_GRAPHICS("2DGraphics", GRAPHICS),
    /**
     * Vector based graphical application.
     */
    VECTOR_GRAPHICS("VectorGraphics", GRAPHICS, X2D_GRAPHICS),
    /**
     * Raster based graphical application.
     */
    RASTER_GRAPHICS("RasterGraphics", GRAPHICS, X2D_GRAPHICS),
    /**
     * 3D based graphical application.
     */
    X3D_GRAPHICS("3DGraphics", GRAPHICS),
    /**
     * Tool to scan a file/text.
     */
    SCANNING("Scanning", GRAPHICS),
    /**
     * Optical character recognition application.
     */
    OCR("OCR", GRAPHICS, SCANNING),
    /**
     * Camera tools, etc.
     */
    PHOTOGRAPHY("Photography", GRAPHICS, OFFICE),
    /**
     * Desktop Publishing applications and Color Management tools.
     */
    PUBLISHING("Publishing", GRAPHICS, OFFICE),
    /**
     * Tool to view e.g. a graphic or pdf file.
     */
    VIEWER("Viewer", GRAPHICS, OFFICE),
    /**
     * A text tool utiliy.
     */
    TEXT_TOOLS("TextTools", UTILITY),
    /**
     * A dictionary
     */
    DICTIONARY("Dictionary", OFFICE, TEXT_TOOLS),
    /**
     * Configuration tool for the GUI.
     */
    DESKTOP_SETTINGS("DesktopSettings", SETTINGS),
    /**
     * A tool to manage hardware components, like sound cards, video cards or printers.
     */
    HARDWARE_SETTINGS("HardwareSettings", SETTINGS),
    /**
     * A tool to manage printers	HardwareSettings.
     */
    PRINTING("Printing", HARDWARE_SETTINGS, SETTINGS),
    /**
     * A package manager application.
     */
    PACKAGE_MANAGER("PackageManager", SETTINGS),
    /**
     * A dial-up program.
     */
    DIALUP("Dialup", NETWORK),
    /**
     * An instant messaging client.
     */
    INSTANT_MESSAGING("InstantMessaging", NETWORK),
    /**
     * A chat client.
     */
    CHAT("Chat", NETWORK),
    /**
     * An IRC client.
     */
    IRC_CLIENT("IRCClient", NETWORK),
    /**
     * Tools like FTP or P2P programs.
     */
    FILE_TRANSFER("FileTransfer", NETWORK),
    /**
     * HAM radio software.
     */
    HAM_RADIO("HamRadio", NETWORK, AUDIO),
    /**
     * A news reader or a news ticker.
     */
    NEWS("News", NETWORK),
    /**
     * A P2P program.
     */
    P2P("P2P", NETWORK),
    /**
     * A tool to remotely manage your PC.
     */
    REMOTE_ACCESS("RemoteAccess", NETWORK),
    /**
     * Telephony via PC.
     */
    TELEPHONY("Telephony", NETWORK),
    /**
     * Telephony tools, to dial a number, manage PBX, ...
     */
    TELEPHONY_TOOLS("TelephonyTools", UTILITY),
    /**
     * Video Conference software.
     */
    VIDEO_CONFERENCE("VideoConference", NETWORK),
    /**
     * A web browser.
     */
    WEB_BROWSER("WebBrowser", NETWORK),
    /**
     * A tool for web developers.
     */
    WebDevelopment("WebDevelopment", NETWORK, DEVELOPMENT),
    /**
     * An app related to MIDI.
     */
    MIDI("Midi", AUDIO_VIDEO, AUDIO),
    /**
     * Just a mixer.
     */
    MIXER("Mixer", AUDIO_VIDEO, AUDIO),
    /**
     * A sequencer
     */
    SEQUENCER("Sequencer", AUDIO_VIDEO, AUDIO),
    /**
     * A tuner.
     */
    TUNER("Tuner", AUDIO_VIDEO, AUDIO),
    /**
     * A TV application.
     */
    TV("TV", AUDIO_VIDEO, AUDIO),
    /**
     * Application to edit audio/video files.
     */
    AUDIO_VIDEO_EDITING("AudioVideoEditing", AUDIO, VIDEO, AUDIO_VIDEO),
    /**
     * Application to play audio/video files.
     */
    PLAYER("Player", AUDIO, VIDEO, AUDIO_VIDEO),
    /**
     * Application to record audio/video files.
     */
    RECORDER("Recorder", AUDIO, VIDEO, AUDIO_VIDEO),
    /**
     * Application to burn a disc.
     */
    DISC_BURNING("DiscBurning", AUDIO_VIDEO),
    /**
     * An action game.
     */
    ACTION_GAME("ActionGame", GAME),
    /**
     * Adventure style game.
     */
    ADVENTURE_GAME("AdventureGame", GAME),
    /**
     * Arcade style game
     */
    ARCADE_GAME("ArcadeGame", GAME),
    /**
     * A board game.
     */
    BOARD_GAME("BoardGame", GAME),
    /**
     * Falling blocks game
     */
    BLOCKS_GAME("BlocksGame", GAME),
    /**
     * A card game.
     */
    CARD_GAME("CardGame", GAME),
    /**
     * A game for kids.
     */
    KIDS_GAME("KidsGame", GAME),
    /**
     * Logic games like puzzles, etc.
     */
    LOGIC_GAME("LogicGame", GAME),
    /**
     * A role playing game.
     */
    ROLE_PLAYING("RolePlaying", GAME),
    /**
     * A simulation game.
     */
    SIMULATION("Simulation", GAME),
    /**
     * A sports game.
     */
    SPORTS_GAME("SportsGame", GAME),
    /**
     * A strategy game.
     */
    STRATEGY_GAME("StrategyGame", GAME),
    /**
     * Software to teach arts.
     */
    ART("Art", EDUCATION),
    /**
     * Construction software.
     */
    CONSTRUCTION("Construction", EDUCATION),
    /**
     * Musical software.
     */
    MUSIC("Music", AUDIO_VIDEO, EDUCATION),
    /**
     * Software to learn foreign languages
     */
    LANGUAGES("Languages", EDUCATION),
    /**
     * Artificial Intelligence software.
     */
    ARTIFICIAL_INTELLIGENCE("ArtificialIntelligence", EDUCATION, SCIENCE),
    /**
     * Astronomy software.
     */
    ASTRONOMY("Astronomy", EDUCATION, SCIENCE),
    /**
     * Biology software.
     */
    BIOLOGY("Biology", EDUCATION, SCIENCE),
    /**
     * Chemistry software.
     */
    CHEMISTRY("Chemistry", EDUCATION, SCIENCE),
    /**
     * ComputerSience software.
     */
    COMPUTER_SCIENCE("ComputerScience", EDUCATION, SCIENCE),
    /**
     * Data visualization software.
     */
    DATA_VISUALIZATION("DataVisualization", EDUCATION, SCIENCE),
    /**
     * Economy software.
     */
    ECONOMY("Economy", EDUCATION),
    /**
     * Electricity software.
     */
    ELECTRICITY("Electricity", EDUCATION, SCIENCE),
    /**
     * Geography software.
     */
    GEOGRAPHY("Geography", EDUCATION),
    /**
     * Geology software.
     */
    GEOLOGY("Geology", EDUCATION, SCIENCE),
    /**
     * Geoscience software.
     */
    GEOSCIENCE("Geoscience", EDUCATION, SCIENCE),
    /**
     * History software.
     */
    HISTORY("History", EDUCATION),
    /**
     * Image Processing software.
     */
    IMAGE_PROCESSING("ImageProcessing", EDUCATION, SCIENCE),
    /**
     * Literature software
     */
    LITERATURE("Literature", EDUCATION),
    /**
     * Math software.
     */
    MATH("Math", EDUCATION, SCIENCE),
    /**
     * Numerical analysis software.
     */
    NUMERICAL_ANALYSIS("NumericalAnalysis", EDUCATION, SCIENCE, MATH),
    /**
     * Medical software.
     */
    MEDICAL_SOFTWARE("MedicalSoftware", EDUCATION, SCIENCE),
    /**
     * Physics software.
     */
    PHYSICS("Physics", EDUCATION, SCIENCE),
    /**
     * Robotics software.
     */
    ROBOTICS("Robotics", EDUCATION, SCIENCE),
    /**
     * Sports software.
     */
    SPORTS("Sports", EDUCATION),
    /**
     * Parallel computing software.
     */
    PARALLEL_COMPUTING("ParallelComputing", EDUCATION, SCIENCE, COMPUTER_SCIENCE),
    /**
     * A simple amusement
     */
    AMUSEMENT("Amusement", new Category[0]),
    /**
     * A tool to archive/backup data.
     */
    ARCHIVING("Archiving", UTILITY),
    /**
     * A tool to manage compressed data/archives
     */
    COMPRESSION("Compression", UTILITY, ARCHIVING),
    /**
     * Electronics software, e.g. a circuit designer.
     */
    ELECTRONICS("Electronics", new Category[0]),
    /**
     * Emulator of another platform, such as a DOS emulator.
     */
    EMULATOR("Emulator", SYSTEM, GAME),
    /**
     * Engineering software, e.g. CAD programs.
     */
    ENGINEERING("Engineering", new Category[0]),
    /**
     * A file tool utility.
     */
    FILE_TOOLS("FileTools", UTILITY, SYSTEM),
    /**
     * A file manager.
     */
    FILE_MANAGER("FileManager", SYSTEM, FILE_TOOLS),
    /**
     * A terminal emulator application.
     */
    TERMINAL_EMULATOR("TerminalEmulator", SYSTEM),
    /**
     * A file system tool.
     */
    FILESYSTEM("Filesystem", SYSTEM),
    /**
     * Monitor application/applet that monitors some resource or activity.
     */
    MONITOR("Monitor", SYSTEM),
    /**
     * A security tool.
     */
    SECURITY("Security", SETTINGS, SYSTEM),
    /**
     * Accessibility.
     */
    ACCESSIBILITY("Accessibility", SETTINGS, UTILITY),
    /**
     * A calculator.
     */
    CALCULATOR("Calculator", UTILITY),
    /**
     * A clock application/applet.
     */
    CLOCK("Clock", UTILITY),
    /**
     * A text editor.
     */
    TEXT_EDITOR("TextEditor", UTILITY),
    /**
     * Help or documentation.
     */
    DOCUMENTATION("Documentation", false),
    /**
     * Important application, core to the desktop such as a file manager or a help browser.
     */
    CORE("Core", false),
    /**
     * Application based on GTK+ libraries.
     */
    GTK("GTK", false),
    /**
     * Application based on Qt libraries.
     */
    QT("Qt", false),
    /**
     * Application based on KDE libraries.
     */
    KDE("KDE", QT),
    /**
     * Application based on GNOME libraries.
     */
    GNOME("GNOME", GTK),
    /**
     * Application based on Motif libraries.
     */
    MOTIF("Motif", false),
    /**
     * Application based on Java GUI libraries, such as AWT or Swing.
     */
    JAVA("Java", false),
    /**
     * Application that only works inside a terminal (text-based or command line application).
     */
    CONSOLE_ONLY("ConsoleOnly", false);

    private final boolean main;
    private final String value;
    private final Set<Category> related;

    Category(String value) {
        this.main = true;
        this.value = value;
        this.related = Set.of();
    }

    Category(String value, Category... related) {
        this.main = false;
        this.value = value;
        this.related = Set.of(related);
    }

    Category(String value, boolean main) {
        this.main = main;
        this.value = value;
        this.related = Set.of();
    }

    public boolean main() {
        return main;
    }

    public Set<Category> related() {
        return related;
    }

    @Override
    public String toString() {
        return value;
    }
}
