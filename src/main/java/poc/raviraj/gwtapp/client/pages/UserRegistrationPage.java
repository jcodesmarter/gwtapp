package poc.raviraj.gwtapp.client.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

import poc.raviraj.gwtapp.client.MasterTypeService;
import poc.raviraj.gwtapp.client.MasterTypeServiceAsync;
import poc.raviraj.gwtapp.client.OkCancelMessageDialog;
import poc.raviraj.gwtapp.client.OkMessageDialog;
import poc.raviraj.gwtapp.client.PleaseWaitDialog;
import poc.raviraj.gwtapp.client.UserService;
import poc.raviraj.gwtapp.client.UserServiceAsync;
import poc.raviraj.gwtapp.shared.Department;
import poc.raviraj.gwtapp.shared.Designation;
import poc.raviraj.gwtapp.shared.Gender;
import poc.raviraj.gwtapp.shared.Role;
import poc.raviraj.gwtapp.shared.User;

public class UserRegistrationPage extends Composite {

	private static UserRegistrationUiBinder uiBinder = GWT.create(UserRegistrationUiBinder.class);

	interface UserRegistrationUiBinder extends UiBinder<Widget, UserRegistrationPage> {
	}

	private static DateTimeFormat dateFormat = DateTimeFormat.getFormat("MM/dd/yyyy");

	@UiField
	Hidden userId;
	@UiField
	Hidden version;
	@UiField
	TextBox firstName;
	@UiField
	TextBox lastName;
	@UiField
	TextBox userName;
	@UiField
	DateBox dateOfJoining;
	@UiField
	ListBox department;
	@UiField
	ListBox designation;
	@UiField
	ListBox role;
	@UiField
	Button saveButton;
	@UiField
	Button resetButton;
	@UiField
	FormPanel userRegistrationForm;
	@UiField
	HorizontalPanel radioButtonPanelForGender;
	@UiField
	Hidden gender;

	private List<Gender> genderList;
	private List<RadioButton> genderRadioButtonList;
	private List<Department> departmentList;
	private List<Designation> designationList;
	private List<Role> roleList;

	private final PleaseWaitDialog pleaseWait = new PleaseWaitDialog();

	private MasterTypeServiceAsync masterTypeService = GWT.create(MasterTypeService.class);
	private UserServiceAsync userService = GWT.create(UserService.class);

	public UserRegistrationPage() {
		this(0L);
	}
	
	public UserRegistrationPage(final Long uid) {
		this(uid, false);
	}

	public UserRegistrationPage(final Long uid, final boolean resetUserIdToZeroForCreatingNewUserCopy) {
		initWidget(uiBinder.createAndBindUi(this));
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {

				pleaseWait.show();
				// setting focus on first name input box
				firstName.setFocus(true);

				// setting default value and date format for date of joining
				// field
				dateOfJoining.setFormat(new DateBox.DefaultFormat(dateFormat));
				dateOfJoining.setValue(new Date(), true);
				dateOfJoining.getTextBox().setReadOnly(true);

				// loading list box options for genders from RPC request
				masterTypeService.getAllGenders(new AsyncCallback<List<Gender>>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("There was an error while fetching gender list");
					}

					@Override
					public void onSuccess(List<Gender> listOfGenders) {
						genderList = listOfGenders;
						genderRadioButtonList = new ArrayList<RadioButton>();
						for (final Gender g : listOfGenders) {
							RadioButton radioButton = new RadioButton("genderOptions");
							radioButton.setText(g.getKeyName());
							radioButton.addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									gender.setValue(String.valueOf(g.getKeyId()));
								}
							});
							genderRadioButtonList.add(radioButton);
							radioButtonPanelForGender.add(radioButton);
						}

						// loading list box options for departments from RPC
						// request
						masterTypeService.getAllDepartments(new AsyncCallback<List<Department>>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("There was an error while fetching department list");
							}

							@Override
							public void onSuccess(List<Department> listOfDepartments) {
								departmentList = listOfDepartments;
								for (Department d : listOfDepartments) {
									department.addItem(d.getKeyName(), String.valueOf(d.getKeyId()));
								}

								// loading list box options for designations
								// from RPC request
								masterTypeService.getAllDesignations(new AsyncCallback<List<Designation>>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert("There was an error while fetching designation list");
									}

									@Override
									public void onSuccess(List<Designation> listOfDesignations) {
										designationList = listOfDesignations;
										for (Designation d : listOfDesignations) {
											designation.addItem(d.getKeyName(), String.valueOf(d.getKeyId()));
										}

										// loading list box options for roles
										// from RPC request
										masterTypeService.getAllRoles(new AsyncCallback<List<Role>>() {

											@Override
											public void onFailure(Throwable caught) {
												Window.alert("There was an error while fetching role list");
											}

											@Override
											public void onSuccess(List<Role> listOfRoles) {
												roleList = listOfRoles;
												for (Role r : listOfRoles) {
													role.addItem(r.getKeyName(), String.valueOf(r.getKeyId()));
												}

												if (uid > 0) {
													// loading user for updation
													userService.find(uid, new AsyncCallback<User>() {

														@Override
														public void onFailure(Throwable caught) {
															Window.alert("Failed to load user information");
														}

														@SuppressWarnings("deprecation")
														@Override
														public void onSuccess(User user) {
															if(!resetUserIdToZeroForCreatingNewUserCopy)
															{
																userId.setValue(String.valueOf(user.getUserId()));
															}
															version.setValue(String.valueOf(user.getVersion()));
															firstName.setText(user.getFirstName());
															lastName.setText(user.getLastName());

															gender.setValue(String.valueOf(user.getGender().getKeyId()));
															GWT.log("Gender to be set: " + gender.getValue());
															for (Gender g : genderList) {
																if (g.getKeyId() == user.getGender().getKeyId()) {
																	GWT.log("Gender to be selected: " + g.getKeyId());
																	for (RadioButton radioBtn : genderRadioButtonList) {
																		GWT.log("Gender Matching: " + radioBtn.getText() + " = " + g.getKeyName());
																		if (radioBtn.getText().equals(g.getKeyName())) {
																			GWT.log("Gender selected: " + radioBtn.getText());
																			radioBtn.setChecked(true);
																		}
																	}
																}
															}

															dateOfJoining.setValue(user.getDateOfJoining());
															userName.setText(user.getUserName());

															for (Department d : departmentList) {
																GWT.log("Comparing Departments: " + d.getKeyId() + " - " + user.getDepartment().getKeyId());
																if (d.getKeyId() == user.getDepartment().getKeyId()) {
																	GWT.log("Matched Departments: " + d.getKeyId() + " - " + user.getDepartment().getKeyId());
																	GWT.log("Index for: " + departmentList.indexOf(d));
																	department.setSelectedIndex(departmentList.indexOf(d) + 1);
																}
															}

															for (Designation d : designationList) {
																if (d.getKeyId() == user.getDesignation().getKeyId()) {
																	designation.setSelectedIndex(designationList.indexOf(d) + 1);
																}
															}

															for (Role r : roleList) {
																if (r.getKeyId() == user.getRole().getKeyId()) {
																	role.setSelectedIndex(roleList.indexOf(r) + 1);
																}
															}
														}
													});
												}

												pleaseWait.hide();
											}

										});
									}

								});
							}

						});
					}

				});

			}
		});

	}

	@UiHandler("saveButton")
	public void saveButton_ClickHandler(ClickEvent event) {

		saveButton.setText("Saving ...");
		saveButton.setEnabled(false);

		User user = new User();
		user.setUserId(Long.valueOf(userId.getValue()));
		user.setVersion(Long.valueOf(version.getValue()));
		user.setFirstName(firstName.getText());
		user.setLastName(lastName.getText());

		Gender genderObj = new Gender();
		genderObj.setKeyId(Long.parseLong(gender.getValue()));
		user.setGender(genderObj);

		user.setDateOfJoining(dateOfJoining.getValue());
		user.setUserName(userName.getText());

		Department departmentObj = new Department();
		departmentObj.setKeyId(Long.parseLong(department.getSelectedValue()));
		user.setDepartment(departmentObj);

		Designation designationObj = new Designation();
		designationObj.setKeyId(Long.parseLong(designation.getSelectedValue()));
		user.setDesignation(designationObj);

		Role roleObj = new Role();
		roleObj.setKeyId(Long.parseLong(role.getSelectedValue()));
		user.setRole(roleObj);

		userService.save(user, new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				saveButton.setEnabled(true);
				saveButton.setText("Save");

				final OkMessageDialog msgbox = new OkMessageDialog("Error", "Failed to register user");
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
				saveButton.setEnabled(true);
				saveButton.setText("Save");
				userRegistrationForm.reset();
				dateOfJoining.setValue(new Date(), true);

				final OkMessageDialog msgbox = new OkMessageDialog("Success", "User has been registered successfully!");
				msgbox.setOkButtonClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						msgbox.hide();
					}
				});
				msgbox.show();
			}

		});
	}

	@UiHandler("resetButton")
	public void resetButton_ClickHandler(ClickEvent event) {
		final OkCancelMessageDialog msgbox = new OkCancelMessageDialog("Warning", "This will reset updated form data. Click Ok to confirm.");
		msgbox.setOkButtonClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				userRegistrationForm.reset();
				dateOfJoining.setValue(new Date(), true);
				msgbox.hide();
			}
		});
		msgbox.setCanelButtonClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				msgbox.hide();
			}
		});
		msgbox.show();
	}
}
