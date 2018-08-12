package com.mercaritask.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.SystemClock;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.mercaritask.R;
import com.mercaritask.data.model.MasterData;
import com.mercaritask.ui.activity.MainActivity;
import com.mercaritask.viewmodel.MainViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule = new ActivityTestRule<>(MainActivity.class);
    private SimpleIdlingResource idlingResource = new SimpleIdlingResource();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @After
    public void complete() {
        IdlingRegistry.getInstance().unregister(idlingResource);
    }

    @Test
    public void testMasterData() {
        final List<MasterData> data = new ArrayList<>();
        idlingResource.setIdleState(false);
        MainViewModel viewModel = ViewModelProviders.of(mMainActivityRule.getActivity()).get(MainViewModel.class);
        viewModel.getMasterData().observeForever(masterData -> {
                    if (masterData != null) {
                        idlingResource.setIdleState(true);
                        data.addAll(masterData);
                        assertThat(data.isEmpty(), is(false));
                        testTabData(viewModel, data);
                    }
                }
        );
    }

    private void testTabData(MainViewModel viewModel, List<MasterData> data) {
        idlingResource.setIdleState(false);
        viewModel.getTabData(data.get(0).data).observeForever(tabData -> {
                    if (tabData != null) {
                        idlingResource.setIdleState(true);
                        assertThat(tabData.isEmpty(), is(false));
                    }
                }
        );

        idlingResource.setIdleState(false);
        viewModel.getTabData(data.get(1).data).observeForever(tabData -> {
                    if (tabData != null) {
                        idlingResource.setIdleState(true);
                        assertThat(tabData.isEmpty(), is(false));
                    }
                }
        );

        idlingResource.setIdleState(false);
        viewModel.getTabData(data.get(2).data).observeForever(tabData -> {
                    if (tabData != null) {
                        idlingResource.setIdleState(true);
                        assertThat(tabData.isEmpty(), is(false));
                    }
                }
        );
    }

    @Test
    public void recyclerView() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.
                scrollToPosition(0));

        SystemClock.sleep(500);

        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.
                scrollToPosition(10));

        SystemClock.sleep(500);

        onView(allOf(withText("MEN"), isDescendantOfA(withId(R.id.tab_layout))))
                .perform(click())
                .check(matches(isDisplayed()));

        SystemClock.sleep(500);

        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.
                scrollToPosition(10));

        SystemClock.sleep(500);

        onView(allOf(withText("WOMAN"), isDescendantOfA(withId(R.id.tab_layout))))
                .perform(click())
                .check(matches(isDisplayed()));

        SystemClock.sleep(500);

        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.
                scrollToPosition(10));

        SystemClock.sleep(500);
    }
}
