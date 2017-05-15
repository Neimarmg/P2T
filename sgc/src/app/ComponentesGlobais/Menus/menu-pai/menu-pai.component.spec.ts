import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuPaiComponent } from './menu-pai.component';

describe('MenuPaiComponent', () => {
  let component: MenuPaiComponent;
  let fixture: ComponentFixture<MenuPaiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenuPaiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuPaiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
