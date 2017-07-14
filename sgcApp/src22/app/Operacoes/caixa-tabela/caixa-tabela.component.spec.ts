import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CaixaTabelaComponent } from './caixa-tabela.component';

describe('CaixaTabelaComponent', () => {
  let component: CaixaTabelaComponent;
  let fixture: ComponentFixture<CaixaTabelaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CaixaTabelaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CaixaTabelaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
