package poc.raviraj.gwtapp.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SingleSelectionModel;

import poc.raviraj.gwtapp.client.pages.UserRegistrationPage;
import poc.raviraj.gwtapp.client.pages.ViewRegisteredUsers;
import poc.raviraj.gwtapp.client.panels.ContentPanel;
import poc.raviraj.gwtapp.shared.User;

public class UserDataContextMenu extends Composite {

	private static UserDataContextMenuUiBinder uiBinder = GWT.create(UserDataContextMenuUiBinder.class);

	interface UserDataContextMenuUiBinder extends UiBinder<Widget, UserDataContextMenu> {
	}

	@UiField
	PopupPanel userDataContextMenu;

	@UiField
	Anchor deleteUser;
	@UiField
	Anchor editUser;
	@UiField
	Anchor createNewCopyOfUser;

	private final PleaseWaitDialog pleaseWait = new PleaseWaitDialog();
	private UserServiceAsync userService = GWT.create(UserService.class);

	private SingleSelectionModel<User> userSelectionModel;
	private SimplePager pager;

	public UserDataContextMenu() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public UserDataContextMenu(SingleSelectionModel<User> userSelectionModel, SimplePager pager) {
		this();
		this.userSelectionModel = userSelectionModel;
		this.pager = pager;
	}

	public PopupPanel getContextMenu() {
		return userDataContextMenu;
	}

	@UiHandler("deleteUser")
	public void deleteUser_ClickHandler(ClickEvent event) {
		userDataContextMenu.hide();

		final User selectedUser = userSelectionModel.getSelectedObject();
		if (selectedUser != null) {
			final OkCancelMessageDialog confirmBox = new OkCancelMessageDialog("Warning", "This will delete the user. Please click Ok to continue deleting.");
			confirmBox.setOkButtonClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					confirmBox.hide();
					pleaseWait.show();
					userService.delete(selectedUser, new AsyncCallback<User>() {
						@Override
						public void onFailure(Throwable caught) {
							pleaseWait.hide();

							final OkMessageDialog msgbox = new OkMessageDialog("Error", "Failed to delete user : " + caught.getMessage());
							msgbox.setOkButtonClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									msgbox.hide();
								}
							});
							msgbox.show();
						}

						@Override
						public void onSuccess(User result) {
							pleaseWait.hide();
							int currentPage = pager.getPage();
							GWT.log("Deleted from Page: " + currentPage);
							final OkMessageDialog msgbox = new OkMessageDialog("Success", "Successfully deleted the user");
							msgbox.setOkButtonClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									msgbox.hide();
								}
							});
							GWT.log("Deleted from Page: " + pager.getPage());
							ContentPanel.changePanelContent(new ViewRegisteredUsers(currentPage));
							msgbox.show();
						}
					});

				}
			});
			confirmBox.setCanelButtonClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					confirmBox.hide();
				}
			});
			confirmBox.show();
		} else {
			pleaseWait.hide();
			new OkMessageDialog("Warning", "Please click to select a user").show();
		}
	}

	@UiHandler("editUser")
	public void editUser_ClickHandler(ClickEvent event) {
		userDataContextMenu.hide();
		User selectedUser = userSelectionModel.getSelectedObject();
		if (selectedUser != null) {
			ContentPanel.changePanelContent(new UserRegistrationPage(selectedUser.getUserId()));
		} else {
			new OkMessageDialog("Warning", "Please click to select a user").show();
		}
	}

	@UiHandler("createNewCopyOfUser")
	public void createNewCopyOfUser_ClickHandler(ClickEvent event) {
		userDataContextMenu.hide();		
		User selectedUser = userSelectionModel.getSelectedObject();
		if (selectedUser != null) {
			ContentPanel.changePanelContent(new UserRegistrationPage(selectedUser.getUserId(), true));
		} else {
			new OkMessageDialog("Warning", "Please click to select a user").show();
		}
	}
}
