import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {ApiService} from "../../shared/service/api.service";
import {MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {User} from "../../shared/interface/user.interface";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Register} from "../../shared/interface/register.interface";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit, AfterViewInit {

  private paginator: MatPaginator;
  private sort: MatSort;
  public userForm: FormGroup;
  choice: boolean = false;
  addUser: boolean = false;
  users: User[] = [];
  dataSource = new MatTableDataSource(this.users);
  tableColumns : string[] = ['userName', 'email', 'userCreated'];

  @ViewChild(MatPaginator) set matPaginator(mp: MatPaginator) {
    this.paginator = mp;
    this.setDataSourceAttributes();
  }
  @ViewChild(MatSort) set matSort(ms: MatSort) {
    this.sort = ms;
    this.setDataSourceAttributes();
  }

  setDataSourceAttributes() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  constructor(private api: ApiService,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.userForm = this.fb.group({
      'user': ['', Validators.required],
      'email' : ['', [Validators.required, Validators.email]],
      'password' : ['', Validators.required],
      'role': ['', Validators.required]
    });
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  showAllUsers(event) {
    if ( event.checked === true ) {
      this.choice = true;
      this.api.getAllUsers().subscribe((data) => {
        this.users = Object.values(data);
        this.dataSource.data = this.users;
      }, error => {
        throw error;
      });
    } else {
      this.choice = false;
    }
  }

  addUsers(event) {
    if ( event.checked === true ) {
      this.addUser = true;
    } else {
      this.addUser = false;
    }
  }

  get email() {
    return this.userForm.get('email');
  }

  get password() {
    return this.userForm.get('password');
  }

  get role() {
    return this.userForm.get('role');
  }

  get user() {
    return this.userForm.get('user');
  }

  onSubmit() {
    console.log(this.userForm.value);
    const user = {} as Register;
    user.email = this.userForm.value.email;
    user.username = this.userForm.value.user;
    user.password = this.userForm.value.password;
    if (this.userForm.value.role == 1) {
      user.role = 'User';
    } else {
      user.role = 'Admin';
    }

    this.api.registerUser(user).subscribe((response) => {
        console.log(response);
      },
      error => {
        console.log(error);
        throw error;
      }
    );
  }
}
