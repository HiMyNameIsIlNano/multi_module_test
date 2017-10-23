import { MyuiPage } from './app.po';

describe('myui App', () => {
  let page: MyuiPage;

  beforeEach(() => {
    page = new MyuiPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
