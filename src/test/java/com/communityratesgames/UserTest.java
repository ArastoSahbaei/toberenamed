package com.communityratesgames;

import com.communityratesgames.dao.CRGDataAccess;
import com.communityratesgames.dao.DataAccessLocal;
import com.communityratesgames.dao.DataAccessRemote;
import com.communityratesgames.domain.*;
import com.communityratesgames.jms.JMSSender;
import com.communityratesgames.model.*;
import com.communityratesgames.rest.UserController;

import com.communityratesgames.transactions.*;
import com.communityratesgames.util.JsonError;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.List;


@RunWith(Arquillian.class)
public class UserTest {

    @Inject
    private UserDataAccess userService;

    @Deployment
    public static WebArchive createDeployment()
    {
        return
                ShrinkWrap.create(WebArchive.class, "test.war")
                        .addClasses(UserController.class)
                        .addClasses(JsonError.class)
                        .addClasses(UserModel.class, CompanyModel.class, GameModel.class, PlatformModel.class, RatingModel.class)
                        .addClasses(User.class, Company.class, Platform.class, Rating.class, UnverifiedGame.class, Game.class)
                        .addClasses(JMSSender.class)
                        .addClasses(DataAccessLocal.class, CRGDataAccess.class, DataAccessRemote.class)
                        .addClasses(CompanyService.class, GameService.class, PlatformService.class, RatingService.class, UnverifiedGameService.class, UserService.class)
                        .addClasses(CompanyDataAccess.class, GameDataAccess.class, PlatformDataAccess.class, RatingDataAccess.class, UnverifiedGameDataAccess.class, UserDataAccess.class)
                        .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void getAllUsers() {
        final List<User> result = userService.showAllUsers();

        Assert.assertNotNull("List ok", result);
    }

}