import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaixaFormComponent } from './caixa-form.component';

describe('CaixaFormComponent', () => {
  let component: CaixaFormComponent;
  let fixture: ComponentFixture<CaixaFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CaixaFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaixaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
