package com.vinoigitare.actions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.vinoigitare.Vinoigitare;
import com.vinoigitare.actions.AbstractAction;
import com.vinoigitare.actions.Action;
import com.vinoigitare.actions.ActionRegistry;

public class ActionRegistryTest {

	@Test(groups = { "fast" })
	public void testActionRegistry() {

		ActionRegistry actionRegistry = new ActionRegistry();

		assertNotNull(actionRegistry.getActionGroupIds());
		assertNotNull(actionRegistry.getActions());

		Action action1 = new TestAction("id_1", "groupId_1", "caption_1",
				"description_1", "iconUrl_1");
		actionRegistry.registerAction(action1);

		assertTrue(actionRegistry.getActionGroupIds().contains("groupId_1"));
		assertTrue(actionRegistry.getActions().contains(action1));

		actionRegistry.unRegisterAction(action1);
		assertFalse(actionRegistry.getActionGroupIds().contains("groupId_1"));
		assertFalse(actionRegistry.getActions().contains(action1));

		Action action2 = new TestAction("id_2", "groupId_2", "caption_2",
				"description_2", "iconUrl_2");
		actionRegistry.registerAction(action2);
		Action action3 = new TestAction("id_3", "groupId_2", "caption_3",
				"description_3", "iconUrl_3");
		actionRegistry.registerAction(action3);
		assertEquals(actionRegistry.getActionGroupIds().size(), 1);
		
		assertEquals(actionRegistry.getActionsForGroup("groupId_2").size(), 2);

	}

	@SuppressWarnings("serial")
	private class TestAction extends AbstractAction {

		private String id;
		private String groupId;
		private String caption;
		private String description;
		private String iconUrl;

		public TestAction(String id, String groupId, String caption,
				String description, String iconUrl) {
			super();
			this.id = id;
			this.groupId = groupId;
			this.caption = caption;
			this.description = description;
			this.iconUrl = iconUrl;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String getGroupId() {
			return groupId;
		}

		@Override
		public String getCaption() {
			return caption;
		}

		@Override
		public String getDescription() {
			return description;
		}

		@Override
		public String getIconUrl() {
			return iconUrl;
		}

		@Override
		public Class<?> getParameterType() {
			return Object.class;
		}

		@Override
		public void execute(Vinoigitare vinoigitare, Object param) {
			System.out.println("Action executed: " + this);
		}
	}
}